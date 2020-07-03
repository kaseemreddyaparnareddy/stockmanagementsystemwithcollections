package com.jfsfeb.stockmanagementsystem.controller;

import com.jfsfeb.stockmanagementsystem.repository.StocksManagementSystemRepository;

public class StockController {
	public static void main(String[] args) {
		StocksManagementSystemRepository.addToDatabase();
		LoginController.MainPage();
	}
}
