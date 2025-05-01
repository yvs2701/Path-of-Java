package com.accenture.travel_bucket_list.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Jackson to deseralize JSON to objects
@Entity
public class TravelBucket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@OneToMany(cascade = CascadeType.ALL)
	private List<ItineraryItem> itineraryItems;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<BudgetItem> budgetItems;
}
