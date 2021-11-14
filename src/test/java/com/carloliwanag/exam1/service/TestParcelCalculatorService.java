package com.carloliwanag.exam1.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carloliwanag.exam1.entity.WeightPriority;
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

	@Test
	void getPriority() {

		List<RuleName> rules = new ArrayList<RuleName>();

		rules.add(RuleName.HEAVY);
		rules.add(RuleName.SMALL);
		rules.add(RuleName.MEDIUM);
		rules.add(RuleName.LARGE);

		List<WeightPriority> priorities = new ArrayList<WeightPriority>();

		WeightPriority heavy = new WeightPriority(2, "Heavy Parcel", "Weight exceeds 10kg", 1L);
		WeightPriority small = new WeightPriority(3, "Small Parcel", "Volume is less than 1500 cubic cm", 2L);
		WeightPriority medium = new WeightPriority(4, "Medium Parcel", "Volume is less than 2500 cubic cm", 3L);
		WeightPriority large = new WeightPriority(5, "Large Parcel", "", 4L);
		
		priorities.add(large);
		priorities.add(medium);
		priorities.add(heavy);
		priorities.add(small);
		
		WeightPriority priority = service.getPriority(rules, priorities);
		
		assertEquals(priority.getRuleName(), "Heavy Parcel");

	}
}
