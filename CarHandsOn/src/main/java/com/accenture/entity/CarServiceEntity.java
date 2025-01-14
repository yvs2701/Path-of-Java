package com.accenture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carservice")
public class CarServiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serviceId;
	private String carType;
	private String carNumber;
	private Integer partId;
	private String username;
	private Integer totalRepairCost;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTotalRepairCost() {
		return totalRepairCost;
	}

	public void setTotalRepairCost(Integer totalRepairCost) {
		this.totalRepairCost = totalRepairCost;
	}

	@Override
	public String toString() {
		return "CarServiceEntity [serviceId=" + serviceId + ", carType=" + carType + ", carNumber=" + carNumber
				+ ", partId=" + partId + ", username=" + username + ", totalRepairCost=" + totalRepairCost + "]";
	}
}
