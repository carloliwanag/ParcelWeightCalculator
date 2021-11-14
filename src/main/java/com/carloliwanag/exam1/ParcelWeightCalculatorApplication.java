package com.carloliwanag.exam1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.carloliwanag.exam1.entity.DimensionCost;
import com.carloliwanag.exam1.entity.WeightPriority;
import com.carloliwanag.exam1.repository.DimensionCostRepository;
import com.carloliwanag.exam1.repository.WeightPriorityRepository;

@SpringBootApplication
public class ParcelWeightCalculatorApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ParcelWeightCalculatorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ParcelWeightCalculatorApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner seed(DimensionCostRepository dimensionCostRepository, WeightPriorityRepository weightPriorityRepository) {
		return (args) -> {
			log.info("Seeding database...");
			
			DimensionCost weight = dimensionCostRepository.save(new DimensionCost("weight", 20.0, "Heavy"));
			DimensionCost small = dimensionCostRepository.save(new DimensionCost("volume", 0.03, "Small" ));
			DimensionCost medium = dimensionCostRepository.save(new DimensionCost("volume", 0.04, "Medium" ));
			DimensionCost large = dimensionCostRepository.save(new DimensionCost("volume", 0.05, "Large" ));
			
			
			weightPriorityRepository.save(new WeightPriority(2, "Heavy Parcel", "Weight exceeds 10kg", weight.getId()));
			weightPriorityRepository.save(new WeightPriority(3, "Small Parcel", "Volume is less than 1500 cubic cm", small.getId()));
			weightPriorityRepository.save(new WeightPriority(4, "Medium Parcel", "Volume is less than 2500 cubic cm", medium.getId()));
			weightPriorityRepository.save(new WeightPriority(5, "Large Parcel", "", large.getId()));
			
		};
	}

}
