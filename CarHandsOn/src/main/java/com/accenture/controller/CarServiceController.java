package com.accenture.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.bean.CarServiceBean;
import com.accenture.service.CarService;

class CarType {
	/* NEW EDITS */
	@NotBlank
	/* END NEW EDITS */
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CarType [value=" + value + "]";
	}
}

@Controller
public class CarServiceController {

	@Autowired
	CarService carService;

	@RequestMapping(path = "/cartype", method = RequestMethod.GET)
	public ModelAndView loadCarTypePage() {
		ModelAndView mav = new ModelAndView("cartype");
		mav.addObject("cartype", new CarType()); // for next page
		return mav;
	}

	@RequestMapping(path = "/submitcartype", method = RequestMethod.POST)
	public ModelAndView loadServiceRegisterationPage(/* NEW EDITS */ @Valid /* END NEW EDITS */ @ModelAttribute("cartype") CarType carType) {
		ModelAndView mav = new ModelAndView("serviceform");

		CarServiceBean bean = new CarServiceBean();
		Map<Integer, String> partDetails = carService.populatePartsDetails(carType.getValue());
		/* NEW EDITS */
		mav.addObject("cartype", carType.getValue());
		/* END NEW EDITS */
		mav.addObject("partDetails", partDetails);
		mav.addObject("carbean", bean); // for next page

		return mav;
	}

	@RequestMapping(path = "/submitform", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(@Valid @ModelAttribute("carbean") CarServiceBean carBean,
			BindingResult result) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("serviceform");
			// TODO ADD ERROR MESSAGES
			/* NEW EDITS */
			if (carBean.getCarType() == null || carBean.getCarType().trim().equals(""))
				mav.addObject("carTypeError", "Car type must be provided!");
			else {
				Map<Integer, String> partDetails = carService.populatePartsDetails(carBean.getCarType());
				mav.addObject("cartype", carBean.getCarType());
				mav.addObject("partDetails", partDetails);
				mav.addObject("carbean", new CarServiceBean()); // for next page
			}
			if (carBean.getCarNumber() == null || carBean.getCarNumber().trim().equals(""))
				mav.addObject("carNumberError", "Car number must be provided!");
			if (carBean.getPartId() == null)
				mav.addObject("partIdError", "Part must be provided!");
			if (carBean.getUsername() == null || carBean.getUsername().trim().equals(""))
				mav.addObject("usernameError", "Username must be provided!");
			/* END NEW EDITS */
			System.err.println(result.getAllErrors());

			return mav;
		}

		carService.registerCarforService(carBean);
		mav.setViewName("success");
		mav.addObject("message", "Your Service id is " + carBean.getServiceId() + " and Total Repair Cost will be "
				+ carBean.getTotalRepairCost());
		return mav;
	}

	@RequestMapping("/displayreport")
	public String processReport(ModelMap map) throws Exception {
		List<CarServiceBean> beans = carService.getAllCarServiceDetails();
		map.addAttribute("carBeans", beans);
		return "report";
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllException(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GeneralizedExceptionHandlerPage");
		mav.addObject("exception", exception);
		mav.addObject("message", exception.getMessage());
		return mav;
	}

}
