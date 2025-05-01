package com.accenture.travel_bucket_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.travel_bucket_list.entity.TravelBucket;

@Repository
public interface TravelBucketRepository extends JpaRepository<TravelBucket, Long> {
}
