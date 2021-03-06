package com.jfsfeb.stockmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystem.services.InvestorService;
import com.jfsfeb.stockmanagementsystem.services.ManagerService;
import com.jfsfeb.stockmanagementsystem.services.ManagerServiceImplementation;
import com.jfsfeb.stockmanagementsystem.validations.StockManagementSystemValidations;

import lombok.extern.log4j.Log4j;

@Log4j
public class ManagerController {
	static Scanner scanner = new Scanner(System.in);
	ManagerBean managerBean = new ManagerBean();
	StockBean stockBean = new StockBean();
	ManagerService managerService = new ManagerServiceImplementation();
	InvestorService investorService = StockManagementSystemFactory.getInvestorServiceImplementation();
	StockManagementSystemValidations validation = new StockManagementSystemValidations();
	int choose;

	public static boolean checkAvailability() {
		boolean isAvailable = false;
		boolean select = false;
		do {
			try {
				isAvailable = scanner.nextBoolean();
				select = true;
			} catch (InputMismatchException e) {
				log.error("Enter Boolean value either True or False");
				select = false;
				scanner.next();
			}
		} while (!select);
		return isAvailable;
	}

	public void managerController() {
		do {
			log.info(" 1. Add Stock");
			log.info(" 2. Remove Stock");
			log.info(" 3. Change Password");
			log.info(" 4. Get List of Stocks");
			log.info(" 0. Logout");
			choose = AdminController.checkChoice();
			switch (choose) {
			case 1:
				log.info("Enter stockId");
				int stockId = 0;
				stockId = (int) (Math.random() * 1000);
				if (stockId <= 100) {
					stockId = stockId + 100;
				}
				log.info(stockId);
				stockBean.setStockId(stockId);
				log.info("Enter stock name");
				String stockName = null;
				double stockPrice = 0.0;
						try{
							stockName = scanner.next();
						}catch (StockManagementSystemExceptions smse) {
							log.error(smse.getMessage());
						}
				stockBean.setStockName(stockName);
				log.info("Enter stock price");
				try {
				 stockPrice = scanner.nextDouble();
				}catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				stockBean.setPrice(stockPrice);
				log.info("Enter availability");
				boolean isAvaliable = checkAvailability();
				stockBean.setAvailable(isAvaliable);
				
				try {
					boolean isAdded = managerService.addStock(stockBean);
					if (isAdded) {
						log.info("Stock added successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 2:
				log.info("Enter Stock Id");
				int id = 0;
				try{
					id = scanner.nextInt();
				} catch (InputMismatchException e) {
					log.error(e.getMessage());
					scanner.nextLine();
				}catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				stockBean.setStockId(id);
				try {
					boolean isRemoved = managerService.removeStock(id);
					if (isRemoved) {
						log.info("Removed successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 3:
				log.info("Enter EmailId");
				String managerEmailId = null, oldPassword = null, newPassword = null;
				try{
					managerEmailId = scanner.next();
				}catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setEmail(managerEmailId);
				log.info("Enter old password");
				try{
					oldPassword = scanner.next();
				}catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setPassword(oldPassword);
				log.info("Enter new password");
				try {
					newPassword = scanner.next();
				}catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setPassword(newPassword);
				try {
					boolean isChanged = managerService.changePassword(managerEmailId, oldPassword, newPassword);
					if (isChanged) {
						log.info("Changed sucessfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 4:
				log.info("List of stocks");
				List<StockBean> stocksList = investorService.getAllStocks();
				for (StockBean stockBean : stocksList) {
					log.info("------------------------------------");
					log.info(stockBean);
				}
				break;

			case 0:
				log.info("logout successful");
				break;
			default:
				log.error("Invalid choice, should choose from 0 to 4");
				break;
			}
		} while (choose != 0);
	}

	public void managerLogin() {
		log.info("Manager Login Page");
		log.info("-----------------");
		log.info("Enter Email id ");
		String managerEmailId = null, managerPassword = null;
				try{
					managerEmailId = scanner.next();
				}catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
		log.info("Enter password");
		try {
			managerPassword = scanner.next();
		}catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}
		try {
			boolean login = managerService.managerLogin(managerEmailId, managerPassword);
			if (login) {
				managerController();
			}
		} catch (StockManagementSystemExceptions smse) {
			log.info(smse.getMessage());
		}
	}

}
