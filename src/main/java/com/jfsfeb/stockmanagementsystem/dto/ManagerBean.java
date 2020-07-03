package com.jfsfeb.stockmanagementsystem.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
public class ManagerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String name;
	@ToString.Exclude
	private String password;
	private String email;
	private String designation;
	private String companyName;

}
