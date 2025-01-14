package com.accenture.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.bean.CarServiceBean;
import com.accenture.bean.PartsBean;
import com.accenture.entity.CarServiceEntity;
import com.accenture.entity.PartsEntity;

@Repository
@Transactional(value = "txManager")
public class CarServiceDAOWrapper {

	@Autowired
	CarServiceDAO carDAO;

	@PersistenceContext
	EntityManager em;

	CarServiceBean convertEntityToBean(CarServiceEntity entity) {
		CarServiceBean bean = new CarServiceBean();
		bean.setCarNumber(entity.getCarNumber());
		bean.setCarType(entity.getCarType());
		bean.setPartId(entity.getPartId());
		bean.setServiceId(entity.getServiceId());
		bean.setTotalRepairCost(entity.getTotalRepairCost());
		bean.setUsername(entity.getUsername());

		return bean;
	}

	PartsBean convertEntityToBean(PartsEntity entity) {
		PartsBean bean = new PartsBean();
		bean.setPartId(entity.getPartId());
		bean.setCarType(entity.getCarType());
		bean.setPartName(entity.getPartName());
		bean.setPrice(entity.getPrice());

		return bean;
	}

	CarServiceEntity convertBeanToEntity(CarServiceBean bean) {
		CarServiceEntity entity = new CarServiceEntity();
		entity.setCarNumber(bean.getCarNumber());
		entity.setCarType(bean.getCarType());
		entity.setPartId(bean.getPartId());
		entity.setServiceId(bean.getServiceId());
		entity.setTotalRepairCost(bean.getTotalRepairCost());
		entity.setUsername(bean.getUsername());

		return entity;
	}

	PartsEntity convertBeanToEntity(PartsBean bean) {
		PartsEntity entity = new PartsEntity();
		entity.setPartId(bean.getPartId());
		entity.setCarType(bean.getCarType());
		entity.setPartName(bean.getPartName());
		entity.setPrice(bean.getPrice());

		return entity;
	}

	public List<CarServiceBean> getAllCarServiceDetails() {
		return carDAO.getAllCarServiceDetails().stream().map(ent -> convertEntityToBean(ent))
				.collect(Collectors.toList());
	}

	@Transactional("txManager")
	public CarServiceBean registerCarForService(CarServiceBean bean) throws Exception {
		CarServiceEntity ent = convertBeanToEntity(bean);
		// em.persist(ent);
		/* NEW CHANGES */
		ent = carDAO.save(ent);
		bean.setServiceId(ent.getServiceId());
		/* END NEW CHANGES */
		return convertEntityToBean(ent);
	}

	public List<PartsBean> populatePartDetails(String carType) {
		List<PartsEntity> parts = em
				.createQuery("select p from PartsEntity p where p.carType=:carType", PartsEntity.class)
				.setParameter("carType", carType).getResultList();
		List<PartsBean> beans = new ArrayList<PartsBean>();

		if (parts != null) {
			for (PartsEntity ent : parts) {
				beans.add(convertEntityToBean(ent));
			}
		}

		return beans;
	}

	public long countUser(String username) {
		return em.createQuery("select c.username from CarServiceEntity c where c.username=:name")
				.setParameter("name", username).getResultList().size();
	}

	public int getPartsPrice(int partId, String carType) {
		PartsEntity p = em
				.createQuery("select p from PartsEntity p where p.partId=:id and carType=:type", PartsEntity.class)
				.setParameter("id", partId).setParameter("type", carType).getSingleResult();
		if (p != null)
			return p.getPrice();
		return 0;
	}

}
