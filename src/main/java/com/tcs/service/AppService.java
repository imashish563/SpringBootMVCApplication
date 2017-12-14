package com.tcs.service;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.jpa.model.AppData;
import com.tcs.repository.AppDataRepository;
import com.tcs.utils.BubbleSort;


@Service
@Transactional(readOnly = false, rollbackFor=Exception.class)
public class AppService {

	private static final Logger LOGGER = Logger.getLogger(AppService.class);
	
	@Autowired
	AppDataRepository appDataRepository;
	
	public String sortNumbers(String numbers) {
		
		LOGGER.info("Entering sortNumbers");
		
		// Converting the comma separated NUMBERS coming from UI into Integer Array
		Integer[] numbersArray = Arrays.stream(numbers.split(","))
		        .map(String::trim)
		        .map(Integer::valueOf)
		        .toArray(Integer[]::new);
		
		
		String sortedArray = sortArray(numbersArray);
		
		LOGGER.info("Exiting sortNumbers");
		return sortedArray;
	}
	
	private String sortArray(Integer[] numbers) {
		
		LOGGER.info("Entering sortArray");
		
		AppData appData = new AppData();
		appData.setNumbersBeforeSort(Arrays.asList(numbers).toString());
		
		// Sort the Array (Using Bubble sort algorithm from Sorting)
		BubbleSort sortUtil = new BubbleSort();
		sortUtil.bubbleSort(numbers);
		
		appData.setNumbersAfterSort(Arrays.asList(numbers).toString());
		appData.setTimeTakenToSort(sortUtil.getTimeTakenToSort());
		appData.setNoOfPositionsSwapped(sortUtil.getNoOfPositionsSwapped());
		
		// Save in DB
		appDataRepository.save(appData);
		
		LOGGER.info("Exiting sortArray");
		
		return Arrays.asList(numbers).toString();
	}
	
	public List<AppData> getAppData() {
		
		return appDataRepository.findAll();
	}
}
