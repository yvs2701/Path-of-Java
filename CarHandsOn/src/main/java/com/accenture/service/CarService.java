package com.accenture.service;

import java.util.List;
import java.util.Map;

import com.accenture.bean.CarServiceBean;

public interface CarService {
	CarServiceBean registerCarforService(CarServiceBean bean) throws Exception;
	Map<Integer, String> populatePartsDetails(String carType);
	List<CarServiceBean> getAllCarServiceDetails() throws Exception;
	int getPartsPrice(int partId, String carType);
	long countUser(String username);
}
