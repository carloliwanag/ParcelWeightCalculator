package com.carloliwanag.exam1.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.carloliwanag.exam1.dto.Discount;
import com.carloliwanag.exam1.entity.DimensionCost;
import com.carloliwanag.exam1.entity.WeightPriority;
import com.carloliwanag.exam1.repository.DimensionCostRepository;
import com.carloliwanag.exam1.repository.WeightPriorityRepository;
import com.carloliwanag.exam1.utils.RuleName;

@Service
public class ParcelCalculatorServiceImpl implements ParcelCalculatorService {

	private static final Logger log = LoggerFactory.getLogger(ParcelCalculatorServiceImpl.class);

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
	public Double computeCost(Double height, Double length, Double width, Double weight, String promoCode) {

		Double cost = 0.0;

		Double volume = this.computeVolume(height, length, width);
		List<RuleName> rules = this.determineRule(volume, weight);

		List<WeightPriority> priorityList = new ArrayList<WeightPriority>();
		priorities.findAll().forEach(priorityList::add);

		WeightPriority wp = this.getPriority(rules, priorityList);

		Optional<DimensionCost> optionalDimensionCost = costs.findById(wp.getDimensionCostId());

		if (optionalDimensionCost.isPresent()) {

			DimensionCost dimensionCost = optionalDimensionCost.get();

			if (dimensionCost.getDimension().equalsIgnoreCase("volume")) {
				cost = dimensionCost.getCostPerDimension() * volume;
			}

			if (dimensionCost.getDimension().equalsIgnoreCase("weight")) {
				cost = dimensionCost.getCostPerDimension() * weight;
			}

			// get discount

			Double discount = this.getDiscount(promoCode);
			
			if (discount > 0) {
				cost = cost * (100 - discount);
			}
			

		}

		return cost;
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

	public WeightPriority getPriority(List<RuleName> rules, List<WeightPriority> priorityList) {
		WeightPriority priority = null;

		for (RuleName rule : rules) {
			WeightPriority fromDB = priorityList.stream().filter(prio -> prio.getRuleName().equals(rule.label))
					.findAny().orElse(null);

			if (priority == null) {
				priority = fromDB;
			} else {

				if (priority.getPriority() > fromDB.getPriority()) {
					priority = fromDB;
				}
			}
		}

		return priority;
	}

	public Double getDiscount(String promoCode) {
		String baseURL = "https://mynt-exam.mocklab.io/voucher/";

		Double discount = 0.0;

		if (promoCode == null) {
			return discount;
		}

		StringBuilder url = new StringBuilder();

		url.append(baseURL);
		url.append(promoCode);
		url.append("?key=apikey");

		RestTemplate restTemplate = new RestTemplate();

		try {

			Discount result = restTemplate.getForObject(url.toString(), Discount.class);

			// check expiry
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date expiry = sdf.parse(result.getExpiry());

			if (expiry.before(new Date())) {
				return discount;
			}

			discount = result.getDiscount();

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return discount;
	}

}
