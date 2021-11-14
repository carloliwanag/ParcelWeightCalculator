package com.carloliwanag.exam1.entity;

import javax.persistence.*;

/**
 * Rule
 * @author carlo
 *
 */
@Entity
public class DimensionCost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dimension_cost_id")
	private Long id;

	private String dimension;
	private String description;

	@Column(name = "cost_per_dimension")
	private Double costPerDimension;

	public DimensionCost() {
	}

	public DimensionCost(String dimension, Double costPerDimension, String description) {
		this.dimension = dimension;
		this.costPerDimension = costPerDimension;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Double getCostPerDimension() {
		return costPerDimension;
	}

	public void setCostPerDimension(Double costPerDimension) {
		this.costPerDimension = costPerDimension;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
