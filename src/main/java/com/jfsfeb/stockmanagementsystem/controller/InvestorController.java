package com.jfsfeb.stockmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystem.services.InvestorService;
import com.jfsfeb.stockmanagementsystem.validations.StockManagementSystemValidations;

import lombok.extern.log4j.Log4j;

@Log4j
public class InvestorController {
	Scanner scanner = new Scanner(System.in);
	InvestorBean investorBean = new InvestorBean();
	StockBean stockBean = new StockBean();
	InvestorService investorService = StockManagementSystemFactory.getInvestorServiceImplementation();
	StockManagementSystemValidations validation = new StockManagementSystemValidations();
	int choose;

	public void investorController() {
		do {
			log.info(" 1. Buy Stock");
			log.info(" 2. List Of Stocks");
			log.info(" 3. Search Stocks");
			log.info(" 0. Logout");
			choose = AdminController.checkChoice();
			switch (choose) {
			case 1:
				log.info("Enter stock name");
				String stockName = null;
				int investorId = 0;
				try {
					stockName = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				stockBean.setStockName(stockName);
				log.info("Enter investor id");
				try {
					investorId = scanner.nextInt();
				} catch (InputMismatchException e) {
					log.error(e.getMessage());
					scanner.nextLine();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				investorBean.setId(investorId);
				try {
					boolean isRequested = investorService.buyStock(stockName, investorId);
					if (isRequested) {

						log.info("Stock Request placed to admin");
					}

				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 2:
				log.info("List of stocks");
				List<StockBean> stocksList = investorService.getAllStocks();
				for (StockBean stockBean : stocksList) {
					log.info(stockBean);
					break;
				}
				break;

			case 3:
				log.info("Searh Results");
				log.info("-------------------------");
				log.info("Enter stock name");
				String stockNamee = null;
				try {
					stockNamee = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error("Please enter proper stock name");
				}
				try {
					StockBean stockSearch = investorService.searchStock(stockNamee);
					log.info("stock found");
					log.info("----------------------------------------------------");
					log.info("Stock Id ------------->" + stockSearch.getStockId());
					log.info("Stock name------------>" + stockSearch.getStockName());
					log.info("Stock price----------->" + stockSearch.getPrice());
					log.info("Stock Availability---->" + stockSearch.isAvailable());
					log.info("----------------------------------------------------");
					
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());

				}
				break;
			case 0:
				log.info("logout successful");
				break;
			default:
				log.error("Invalid choice, should choose from 0 to 3");
				break;
			}
		} while (choose != 0);
	}

	public void investorLogin() {
		log.info("Investor Login Page");
		log.info("-----------------");
		log.info("Enter Email id");
		String investorEmailId = null, investorPassword = null;
		try {
			investorEmailId = scanner.next();
		} catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}
		log.info("Enter password");
		try {
			investorPassword = scanner.next();
		} catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}
		try {
			boolean login = investorService.investorLogin(investorEmailId, investorPassword);
			if (login) {
				investorController();
			}
		} catch (StockManagementSystemExceptions smse) {
			log.info(smse.getMessage());
		}
	}

}
