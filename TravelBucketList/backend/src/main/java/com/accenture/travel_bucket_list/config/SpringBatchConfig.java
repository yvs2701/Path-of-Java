package com.accenture.travel_bucket_list.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import com.accenture.travel_bucket_list.entity.TravelBucket;
import com.accenture.travel_bucket_list.repository.TravelBucketRepository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpringBatchConfig {
	/* 
	 * MAKE SURE JSON DOES NOT CONTAIN ID FIELDS (let the objects have NULL values)
	 * SINCE THE ENTITIES ARE USING AUTO GENERATED IDs
	 * Otherwise an error will be thrown:
	 * jakarta.persistence.OptimisticLockException: Row was updated or deleted by another transaction
	 * (or unsaved-value mapping was incorrect)
	 */
	private final Resource inputResourcePath;

	private final EntityManagerFactory emf;
	
	public SpringBatchConfig(@Value("classpath:data/input.json") Resource inputResourcePath, @Autowired EntityManagerFactory emf) {
		this.inputResourcePath = inputResourcePath;
		this.emf = emf;
	}

	@Bean
	public JsonItemReader<TravelBucket> itemReader() {
		return new JsonItemReaderBuilder<TravelBucket>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(TravelBucket.class)).resource(inputResourcePath)
				.strict(true) // throws error if resource does not exist
				.name("travelBucketJsonItemReader").build();
	}

	@Bean
	public ItemProcessor<TravelBucket, TravelBucket> itemProcessor() {
		return travelBucket -> {
			log.debug("\nProcessing TravelBucket: {}\n", travelBucket);
			return travelBucket;
		};
	}

	@Bean
	@Transactional
	public JpaItemWriter<TravelBucket> itemWriter(TravelBucketRepository repository) {
		return new JpaItemWriterBuilder<TravelBucket>().entityManagerFactory(emf).build();
	}

	@Bean
	public Step importFromJson(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemReader<TravelBucket> reader, ItemProcessor<TravelBucket, TravelBucket> processor,
			ItemWriter<TravelBucket> writer) {
		return new StepBuilder("importFromJson", jobRepository)
				.<TravelBucket, TravelBucket>chunk(10, transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.transactionManager(transactionManager)
				.build();
	}

	@Bean
	public Job importTravelBucketsJob(JobRepository jobRepository, Step importFromJson) {
		return new JobBuilder("importTravelBucketsJob", jobRepository)
				.preventRestart()
				.incrementer(new RunIdIncrementer())
				.start(importFromJson).on("FAILED").end()
				.from(importFromJson).on("*").end()
				.end()
				.build();
	}
}
