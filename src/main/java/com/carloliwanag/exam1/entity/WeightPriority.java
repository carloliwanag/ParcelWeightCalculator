package com.carloliwanag.exam1.entity;

import javax.persistence.*;

/**
 * Priority
 * @author carlo
 *
 */
@Entity
public class WeightPriority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "weight_priority_id")
	private Integer id;

	private Integer priority;

	@Column(name = "rule_name")
	private String ruleName;

	private String condition;

	@Column(name = "dimension_cost_id")
	private Integer dimensionCostId;

	public WeightPriority() {
	}

	public WeightPriority(Integer priority, String ruleName, String condition, Integer dimensionCostId) {
		this.priority = priority;
		this.ruleName = ruleName;
		this.condition = condition;
		this.dimensionCostId = dimensionCostId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getDimensionCostId() {
		return dimensionCostId;
	}

	public void setDimensionCostId(Integer dimensionCostId) {
		this.dimensionCostId = dimensionCostId;
	}

}
