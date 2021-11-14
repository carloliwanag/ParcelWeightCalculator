package com.carloliwanag.exam1.utils;

public enum RuleName {
	HEAVY("Heavy Parcel"), SMALL("Small Parcel"), MEDIUM("Medium Parcel"), LARGE("Large Parcel"), REJECT("Reject");

	public final String label;

	private RuleName(String label) {
		this.label = label;
	}
}
