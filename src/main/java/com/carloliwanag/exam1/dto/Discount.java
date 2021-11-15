package com.carloliwanag.exam1.dto;

public class Discount {
	private String code;
	private Double discount;
	private String expiry;
	
	public Discount() {
		
	}

	public Discount(String code, Double discount, String expiry) {
		super();
		this.code = code;
		this.discount = discount;
		this.expiry = expiry;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
}
