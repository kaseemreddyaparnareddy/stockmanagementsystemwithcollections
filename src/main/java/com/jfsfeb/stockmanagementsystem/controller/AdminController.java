package com.jfsfeb.stockmanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jfsfeb.stockmanagementsystem.dto.AdminBean;
import com.jfsfeb.stockmanagementsystem.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystem.services.AdminService;
import com.jfsfeb.stockmanagementsystem.validations.StockManagementSystemValidations;

import lombok.extern.log4j.Log4j;

@Log4j
public class AdminController {
	public static Scanner scanner = new Scanner(System.in);
	AdminBean adminBean = new AdminBean();
	StockRequestBean stockRequestBean = new StockRequestBean();
	CompanyBean companyBean = new CompanyBean();
	ManagerBean managerBean = new ManagerBean();
	AdminService adminService = StockManagementSystemFactory.getAdminServiceImplementation();
	StockManagementSystemValidations validation = new StockManagementSystemValidations();

	public static int checkChoice() {
		boolean select = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				select = true;
			} catch (InputMismatchException e) {
				select = false;
				log.error("Invalid input,Choice Should Contain Only Digits like 1 or 2");
				scanner.next();
			}
		} while (!select);

		return choice;
	}

	int choose;

	public void adminController() {

		do {
			log.info(" 1. Add Manager");
			log.info(" 2. Remove Manager");
			log.info(" 3. Add Company");
			log.info(" 4. Remove Company");
			log.info(" 5. Get List Of Investors");
			log.info(" 6. Investors Stocks Request");
			log.info(" 7. Accept Stocks");
			log.info(" 8. Get List Of Managers");
			log.info(" 9. Get List Of Companies");
			log.info(" 0. Logout");
			choose = checkChoice();

			switch (choose) {
			case 1:
				log.info("Enter Manager Id");
				int managerId = 0;
				String managerName = null, managerEmail = null, password = null, companyName = null;
				managerId = (int) (Math.random() * 1000);
				if (managerId <= 100) {
					managerId = managerId + 100;
				}
				log.info(managerId);
				managerBean.setUserId(managerId);
				log.info("Enter manager name");
				try {
					managerName = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setName(managerName);
				scanner.nextLine();
				log.info("Enter manager Email");
				try {
					managerEmail = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setEmail(managerEmail);
				scanner.nextLine();
				log.info("Enter password");
				try {
					password = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setPassword(password);
				scanner.nextLine();
				log.info("Enter Company name");
				try {
					companyName = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setCompanyName(companyName);
				try {
					boolean isAdded = adminService.addManager(managerBean);
					if (isAdded) {
						log.info("Manager added successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 2:
				log.info("Remove manager");
				int id = 0;
				String name = null;
				log.info("Enter manager Id");
				try {
					id = scanner.nextInt();
				} catch (InputMismatchException e) {
					log.error(e.getMessage());
					scanner.nextLine();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setUserId(id);
				log.info("Enter manager name");
				try {
					name = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setName(name);
				try {
					boolean isRemoved = adminService.removeManager(id, name);
					if (isRemoved) {
						log.info("Removed successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 3:
				log.info("Add company");
				log.info("Enter company name");
				String companyNamee = null, companyBranch = null, companyState = null;
				try {
					companyNamee = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				log.info("Enter company branch");
				try {
					companyBranch = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				log.info("Enter company state");
				try {
					companyState = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				companyBean.setCompanyName(companyNamee);
				companyBean.setBranch(companyBranch);
				companyBean.setState(companyState);
				try {
					boolean isToAdd = adminService.addCompany(companyBean);
					if (isToAdd) {
						log.info("company added successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 4:
				log.info("Remove company");
				log.info("Enter company Id");
				int id1 = 0;
				String companyName1 = null;
				try {
					id1 = scanner.nextInt();
				} catch (InputMismatchException e) {
					log.error(e.getMessage());
					scanner.nextLine();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				companyBean.setCompanyId(id1);
				log.info("Enter company name");
				try {
					companyName1 = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setName(companyName1);
				try {
					boolean isRemoved = adminService.removeCompany(id1, companyName1);
					if (isRemoved) {
						log.info("Removed successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 5:
				log.info("List of investors");
				List<InvestorBean> investorsList = adminService.getAllInvestors();
				for (InvestorBean investor : investorsList) {
					System.out.println("---------------------------------------------------");
					System.out.println("Investor Id------>" +investor.getId());
					System.out.println("Investor name---->" +investor.getName());
					System.out.println("Investor stocks-->" +investor.getNumberOfStocks());
					System.out.println("Investor EmailId-->"+investor.getEmail());
					System.out.println("---------------------------------------------------");
				}


				break;
			case 6:
				log.info("Get investors stocks request");
				List<StockRequestBean> reqInfo = adminService.investorRequest();
				for (StockRequestBean investorRequest : reqInfo) {
					log.info("---------------------------------------------------");
					log.info("Investor Id--------->" + investorRequest.getInvestorId());
					log.info("Investor stockname-->" + investorRequest.getStockName());
					log.info("---------------------------------------------------");
				}
				break;

			case 7:
				log.info("Stocks to be accepted");
				log.info("Enter stock name");
				int investorId = 0;
				String stockName = null;
				try {
					stockName = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				stockRequestBean.setStockName(stockName);
				log.info("Enter investorid");
				try {
					investorId = scanner.nextInt();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				stockRequestBean.setInvestorId(investorId);
				log.info("-------------------------");
				try {
					boolean isAccepted = adminService.acceptStock(stockName, investorId);
					if (isAccepted) {
						log.info("Stock accepeted");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 8:
				log.info("List of managers");
				List<ManagerBean> managersList = adminService.getAllManagers();
				for (ManagerBean managers : managersList) {
					System.out.println("---------------------------------------------------");
					System.out.println("Manager Id------>" +managers.getUserId());
					System.out.println("Manager name---->" +managers.getName());
					System.out.println("Manager EmailId->"+managers.getEmail());
					System.out.println("Company name---->"+managers.getCompanyName());
					System.out.println("---------------------------------------------------");
				}

				break;
			case 9:
				log.info("List of companies");
				List<CompanyBean> companyList = adminService.getAllCompanies();
				for (CompanyBean company : companyList) {
					System.out.println("---------------------------------------------------");
					System.out.println("Company Id------>" +company.getCompanyId());
					System.out.println("Company name---->" +company.getCompanyName());
					System.out.println("Company branch-->" +company.getBranch());
					System.out.println("Company state--->" +company.getState());
					System.out.println("---------------------------------------------------");
				}

				break;
			case 0:
				log.info("logout sucessfull");
				break;
			default:
				log.error("Invalid choice, should choose from 0 to 9");
				break;
			}

		} while (choose != 0);

	}

	public void adminLogin() {
		log.info("Admin Login Page");
		log.info("-----------------");
		log.info("Enter Email id ");
		String adminEmailId = null, adminPassword = null;
		try {
			adminEmailId = scanner.next();
		} catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}

		log.info("Enter password");
		try {
			adminPassword = scanner.next();
		} catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}

		try {
			boolean login = adminService.adminLogin(adminEmailId, adminPassword);
			if (login) {
				adminController();
			}
		} catch (StockManagementSystemExceptions smse) {
			log.info(smse.getMessage());
		}
	}

}
