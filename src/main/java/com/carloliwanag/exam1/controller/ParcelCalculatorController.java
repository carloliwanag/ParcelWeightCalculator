package com.carloliwanag.exam1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carloliwanag.exam1.service.ParcelCalculatorService;

@RestController
public class ParcelCalculatorController {
	
	private ParcelCalculatorService parcelCalculatorService;
	
	@Autowired
	public void setParcelCalculatorService(ParcelCalculatorService parcelCalculatorService) {
		this.parcelCalculatorService = parcelCalculatorService;
	}
	
	@GetMapping("/compute")
	public Double computeCost(@RequestParam Double length, @RequestParam Double width, @RequestParam Double height, @RequestParam Double weight, @RequestParam Optional<String> promoCode) {
		return this.parcelCalculatorService.computeCost(height, length, width, weight, promoCode.isPresent() ? promoCode.get() : null);
	}
}
