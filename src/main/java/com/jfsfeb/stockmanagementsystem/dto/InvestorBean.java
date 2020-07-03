package com.jfsfeb.stockmanagementsystem.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
public class InvestorBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	@ToString.Exclude
	private String password;
	private long account_number;
	private int numberOfStocks;
	private long mobile_number;

}
