package com.carloliwanag.exam1.service;


import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carloliwanag.exam1.repository.DimensionCostRepository;
import com.carloliwanag.exam1.repository.WeightPriorityRepository;
import com.carloliwanag.exam1.utils.RuleName;

@ExtendWith(MockitoExtension.class)
public class TestParcelCalculatorService {

	
	ParcelCalculatorService service;
	
	@Mock
	private DimensionCostRepository dimensionCostRepo;
	
	@Mock
	private WeightPriorityRepository weightPrioRepo;
	
	
	@BeforeEach
	public void setup() {
		service = new ParcelCalculatorServiceImpl(dimensionCostRepo, weightPrioRepo);
	}
	
	@Test
	public void computeVolume() {
		
		Double height = 2.0;
		Double lenght = 3.0;
		Double width = 1.0;
				
		Double volume = service.computeVolume(height, lenght, width);
		
		assertEquals(volume.intValue(), 6);
	}
	
	@Test
	public void determineRule() {
			
		List<RuleName> heavy = service.determineRule(4.0, 11.0);
		
		assertEquals(heavy.size(), 3);
	}
	
}
