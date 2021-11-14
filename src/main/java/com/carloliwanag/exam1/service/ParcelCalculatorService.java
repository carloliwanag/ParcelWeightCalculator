package com.carloliwanag.exam1.service;

import java.util.List;

import com.carloliwanag.exam1.utils.RuleName;

public interface ParcelCalculatorService {
	Double computeVolume(Double height, Double length, Double width);
	Double computeCost(Double height, Double length, Double width, Double weight);
	List<RuleName> determineRule(Double volume, Double weight); 
}
