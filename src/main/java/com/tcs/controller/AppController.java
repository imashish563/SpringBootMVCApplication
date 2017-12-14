package com.tcs.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcs.jpa.model.AppData;
import com.tcs.service.AppService;


@Controller
@RequestMapping("/app")
public class AppController {

	private static final Logger LOGGER = Logger.getLogger(AppController.class);
	
	@Autowired
	AppService appService;
	
	
	@RequestMapping(value="/sort-numbers", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		
		model.put("appData", new AppData());
	    return "sort-numbers";
	}
	
	
	@RequestMapping(value = "/sortNumbers", method = RequestMethod.POST)
	public String sortNumbers(ModelMap model, @Valid AppData appData, BindingResult result) {
		
		LOGGER.info("Entering sortNumbers");
		
		if (result.hasErrors()) {
			model.put("appData", new AppData());
		    return "redirect:/app/sort-numbers";
		}
		
		appService.sortNumbers(appData.getNumbersBeforeSort());
		
		model.put("appData", appService.getAppData());
		
		LOGGER.info("Exiting sortNumbers");
		return "redirect:/app/list-appData";
	}
	
	@RequestMapping(value = "/list-appData", method = RequestMethod.GET)
	public String getAppData(ModelMap model) {
		
		LOGGER.info("Entering getAppData");
		
		model.put("appData", appService.getAppData());
		
		LOGGER.info("Exiting getAppData");
		return "list-appData";
	}
}
