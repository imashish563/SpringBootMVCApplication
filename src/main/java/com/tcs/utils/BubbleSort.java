package com.tcs.utils;

public class BubbleSort {
	
	private Float timeTakenToSort = 0f;
	private Integer noOfPositionsSwapped = 0;
	
	public BubbleSort bubbleSort(Integer arr[]) {
		
		long startTime = System.currentTimeMillis();
		
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap temp and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					
					noOfPositionsSwapped++;
				}
			}
		}
		
		long stopTime = System.currentTimeMillis();
		timeTakenToSort = (float) ((stopTime - startTime) / 1000);
		
		return this;
	}

	public Float getTimeTakenToSort() {
		return timeTakenToSort;
	}

	public void setTimeTakenToSort(Float timeTakenToSort) {
		this.timeTakenToSort = timeTakenToSort;
	}

	public Integer getNoOfPositionsSwapped() {
		return noOfPositionsSwapped;
	}

	public void setNoOfPositionsSwapped(Integer noOfPositionsSwapped) {
		this.noOfPositionsSwapped = noOfPositionsSwapped;
	}
}