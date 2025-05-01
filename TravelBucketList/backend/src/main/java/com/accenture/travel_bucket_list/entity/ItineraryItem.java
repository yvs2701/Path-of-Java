package com.accenture.travel_bucket_list.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Jackson to deseralize JSON to objects
@Entity
public class ItineraryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String detail;

	public ItineraryItem(@NotBlank String detail) {
		super();
		this.detail = detail;
	}
}
