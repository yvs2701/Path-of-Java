package com.accenture.travel_bucket_list;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.accenture.travel_bucket_list.repository.TravelBucketRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TravelBucketListApplication {

	private final JobLauncher jobLauncher;

	private final Job importTravelBucketsJob;

	@Autowired
	public TravelBucketListApplication(JobLauncher jobLauncher, Job importTravelBucketsJob) {
		super();
		this.jobLauncher = jobLauncher;
		this.importTravelBucketsJob = importTravelBucketsJob;
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelBucketListApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(TravelBucketRepository repository) {
		return args -> {
//			load dummy data
			long entCount = repository.count();
			log.info("Number of Entities in the database: {}", entCount);

			if (entCount == 0) {
				log.info("Loading dummy data!");
				final JobParameters jobParameters = new JobParametersBuilder()
						.addLong("startAt", System.currentTimeMillis())
						.toJobParameters();
				jobLauncher.run(importTravelBucketsJob, jobParameters);
			}

			/* TravelBucket parisTrip = new TravelBucket();
			parisTrip.setTitle("Paris Trip");
			parisTrip.setDescription("Discover the charm of Paris");
			parisTrip.setItineraryItems(Arrays.asList(new ItineraryItem("Visit the Eiffel Tower"),
					new ItineraryItem("Stroll along the Seine"), new ItineraryItem("Attend Indila's concert")));
			parisTrip.setBudgetItems(Arrays.asList(new BudgetItem("Flight", 11000.0), new BudgetItem("Hotel", 6700.0)));

			TravelBucket delhiTrip = new TravelBucket();
			delhiTrip.setTitle("Delhi Trip");
			delhiTrip.setDescription("Explore the capital of India");
			delhiTrip.setItineraryItems(Arrays.asList(new ItineraryItem("Visit the Qutab Minar"),
					new ItineraryItem("Try Chole Bhature"), new ItineraryItem("Visit India Gate in the night")));
			delhiTrip.setBudgetItems(Arrays.asList(new BudgetItem("Flight", 2256.0), new BudgetItem("Hotel", 955.0)));

			TravelBucket jammuTrip = new TravelBucket();
			jammuTrip.setTitle("Jammu Trip");
			jammuTrip.setDescription("Visting the heaven on Earth");
			jammuTrip.setItineraryItems(Arrays.asList(new ItineraryItem("Visit Vaishno Devi temple"),
					new ItineraryItem("Trek to Srinagar"), new ItineraryItem("Visit nearby monasteries")));
			jammuTrip.setBudgetItems(Arrays.asList(new BudgetItem("Rope Climbing", 200.0),
					new BudgetItem("Hotel", 2000.0), new BudgetItem("Train", 1773.0)));

			TravelBucket bengaluruTrip = new TravelBucket();
			bengaluruTrip.setTitle("Bengaluru Trip");
			bengaluruTrip.setDescription("IT hub of India");
			bengaluruTrip.setItineraryItems(Arrays.asList(new ItineraryItem("Trek to Nandi Hills"),
					new ItineraryItem("Spend a day at Isha Yoga center"),
					new ItineraryItem("Say hi to your colleague at Accenture")));
			bengaluruTrip
					.setBudgetItems(Arrays.asList(new BudgetItem("Flight", 2200.0), new BudgetItem("Airbnb", 6000.0)));

//			save the data in the repository
			repository.save(parisTrip);
			repository.save(delhiTrip);
			repository.save(jammuTrip);
			repository.save(bengaluruTrip);

			log.info("Saved dummy data in the repository!"); */
		};
	}
}
