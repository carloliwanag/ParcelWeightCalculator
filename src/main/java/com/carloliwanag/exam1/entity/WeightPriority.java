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
	private Long id;

	private Integer priority;

	@Column(name = "rule_name")
	private String ruleName;

	private String condition;

	@Column(name = "dimension_cost_id")
	private Long dimensionCostId;

	public WeightPriority() {
	}

	public WeightPriority(Integer priority, String ruleName, String condition, Long dimensionCostId) {
		this.priority = priority;
		this.ruleName = ruleName;
		this.condition = condition;
		this.dimensionCostId = dimensionCostId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getDimensionCostId() {
		return dimensionCostId;
	}

	public void setDimensionCostId(Long dimensionCostId) {
		this.dimensionCostId = dimensionCostId;
	}

}
