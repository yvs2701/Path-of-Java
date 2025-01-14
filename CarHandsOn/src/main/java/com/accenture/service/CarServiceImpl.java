package com.accenture.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.Exceptions.CarNotServiceException;
import com.accenture.bean.CarServiceBean;
import com.accenture.bean.PartsBean;
import com.accenture.dao.CarServiceDAOWrapper;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarServiceDAOWrapper carDAO;

	@Override
	public CarServiceBean registerCarforService(CarServiceBean bean) throws Exception {
		if (countUser(bean.getUsername()) >= 3)
			throw new CarNotServiceException("Same user cannot be resgistered for more than 3 car services.");
		
		int price = getPartsPrice(bean.getPartId(), bean.getCarType());
		if (bean.getCarType().equals("Maruti"))
			price += 400;
		else if (bean.getCarType().equals("Hyundai"))
			price += 500;
		
		bean.setTotalRepairCost(price);
		CarServiceBean res = carDAO.registerCarForService(bean);
		return res;
	}

	@Override
	public long countUser(String username) /*throws CarNotServiceException*/ {
		long res = carDAO.countUser(username);
		return res;
	}

	@Override
	public int getPartsPrice(int partId, String carType) {
		int res = carDAO.getPartsPrice(partId, carType);
		return res;
	}

	@Override
	public Map<Integer, String> populatePartsDetails(String carType) {
		List<PartsBean> res = carDAO.populatePartDetails(carType);
		Map<Integer, String> map = res.stream().collect(Collectors.toMap(PartsBean::getPartId, PartsBean::getPartName));
		return map;
	}

	@Override
	public List<CarServiceBean> getAllCarServiceDetails() throws Exception {
		List<CarServiceBean> res = carDAO.getAllCarServiceDetails();
		return res;
	}
}
