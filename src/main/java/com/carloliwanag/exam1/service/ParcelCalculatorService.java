package com.carloliwanag.exam1.service;

import java.util.List;

import com.carloliwanag.exam1.entity.WeightPriority;
import com.carloliwanag.exam1.utils.RuleName;

public interface ParcelCalculatorService {
	Double computeVolume(Double height, Double length, Double width);
	Double computeCost(Double height, Double length, Double width, Double weight, String promoCode);
	List<RuleName> determineRule(Double volume, Double weight); 
	WeightPriority getPriority(List<RuleName> rules, List<WeightPriority> priorityList);
	Double getDiscount(String promoCode);
}
