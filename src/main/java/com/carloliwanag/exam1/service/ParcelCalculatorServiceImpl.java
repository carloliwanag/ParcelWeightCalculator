package com.carloliwanag.exam1.service;

import org.springframework.stereotype.Service;

@Service
public class ParcelCalculatorServiceImpl implements ParcelCalculatorService {

	@Override
	public Integer computeVolume(Integer height, Integer length, Integer width) {
		// TODO Auto-generated method stub
		return height * length * width;
	}

	@Override
	public Integer computeCost(Integer height, Integer length, Integer width) {
		// TODO Auto-generated method stub
		return null;
	}

}
