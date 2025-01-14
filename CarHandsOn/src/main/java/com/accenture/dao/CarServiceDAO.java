package com.accenture.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import com.accenture.entity.CarServiceEntity;

@RepositoryDefinition(domainClass = CarServiceEntity.class, idClass = Integer.class)
public interface CarServiceDAO {
	CarServiceEntity save(CarServiceEntity entity);

	@Query(name = "getAllCarEnt")
	List<CarServiceEntity> getAllCarServiceDetails();
}
