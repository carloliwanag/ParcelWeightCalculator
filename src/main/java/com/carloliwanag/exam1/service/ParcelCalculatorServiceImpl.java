package com.carloliwanag.exam1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carloliwanag.exam1.entity.DimensionCost;
import com.carloliwanag.exam1.entity.WeightPriority;
import com.carloliwanag.exam1.repository.DimensionCostRepository;
import com.carloliwanag.exam1.repository.WeightPriorityRepository;
import com.carloliwanag.exam1.utils.RuleName;

@Service
public class ParcelCalculatorServiceImpl implements ParcelCalculatorService {

	
	private DimensionCostRepository costs;
	
	
	private WeightPriorityRepository priorities;
	
	
	public ParcelCalculatorServiceImpl(final DimensionCostRepository costs, final WeightPriorityRepository priorities) {
		this.costs = costs;
		this.priorities = priorities;
	}
	
	@Override
	public Double computeVolume(Double height, Double length, Double width) {
		return height * length * width;
	}

	@Override
	public Double computeCost(Double height, Double length, Double width, Double weight) {
		// TODO Auto-generated method stub
		
		Double volume = this.computeVolume(height, length, width);
		List<RuleName> rules = this.determineRule(volume, weight);
		
		List<WeightPriority> priorityList = new ArrayList<WeightPriority>();
		priorities.findAll().forEach(priorityList::add);
		WeightPriority wp = this.getPriority(rules, priorityList);
		
		Optional<DimensionCost> optionalDimensionCost = costs.findById(wp.getDimensionCostId());
		
		
		if (optionalDimensionCost.isPresent()) {
			
			DimensionCost dimensionCost = optionalDimensionCost.get();
			
			if (dimensionCost.getDimension().equalsIgnoreCase("volume")) {
				return dimensionCost.getCostPerDimension() * volume;
			}
			
			
			if (dimensionCost.getDimension().equalsIgnoreCase("weight")) {
				return dimensionCost.getCostPerDimension() * weight;
			}
			
			// throw an exception here
			
		}
		
		// Throw an exception here
		return null;
	}

	@Override
	public List<RuleName> determineRule(Double volume, Double weight) {
		
		List<RuleName> rules = new ArrayList<RuleName>();
		
		if (weight > 50) {
			rules.add(RuleName.REJECT);
		}
		
		if (weight > 10) {
			rules.add(RuleName.HEAVY);
		}
		
		if (volume < 1500) {
			rules.add(RuleName.SMALL);
		}
		
		if (volume < 2500) {
			rules.add(RuleName.MEDIUM);
		}
		
		if (volume > 2500) {
			rules.add(RuleName.LARGE);
		}
		
		return rules;
	}
	
	
	private WeightPriority getPriority(List<RuleName> rules, List<WeightPriority> priorityList) {
		 WeightPriority priority = null;
				
		 
		 for (RuleName rule : rules) {
			 WeightPriority fromDB = priorityList.stream().filter(prio -> prio.getRuleName().equals(rule.label)).findAny().orElse(null);
				
				if (priority == null) {
					priority = fromDB;
				} else {
					
					if (priority.getPriority() < fromDB.getPriority()) {
						priority = fromDB;
					}
				}
		 }
						
	    return priority;
	}

}
