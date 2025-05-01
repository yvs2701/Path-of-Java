package com.accenture.travel_bucket_list.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Jackson to deseralize JSON to objects
@Entity
public class BudgetItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String itemName;

	@NotNull
	private Double price;

	public BudgetItem(@NotBlank String itemName, @NotNull Double price) {
		super();
		this.itemName = itemName;
		this.price = price;
	}
}
