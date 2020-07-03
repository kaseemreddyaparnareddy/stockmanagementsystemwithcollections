package com.jfsfeb.stockmanagementsystem.factory;

import com.jfsfeb.stockmanagementsystem.dao.AdminDAO;
import com.jfsfeb.stockmanagementsystem.dao.AdminDAOImplementation;
import com.jfsfeb.stockmanagementsystem.dao.InvestorDAO;
import com.jfsfeb.stockmanagementsystem.dao.InvestorDAOImplementation;
import com.jfsfeb.stockmanagementsystem.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystem.dao.ManagerDAOImplementation;
import com.jfsfeb.stockmanagementsystem.services.AdminService;
import com.jfsfeb.stockmanagementsystem.services.AdminServiceImplementation;
import com.jfsfeb.stockmanagementsystem.services.InvestorService;
import com.jfsfeb.stockmanagementsystem.services.InvestorServiceImplementation;
import com.jfsfeb.stockmanagementsystem.services.ManagerService;
import com.jfsfeb.stockmanagementsystem.services.ManagerServiceImplementation;

public class StockManagementSystemFactory {

	private StockManagementSystemFactory() {
		
	}
	public static AdminDAO getAdminDAOImplementation() {
		AdminDAO admin = new AdminDAOImplementation();
		return admin;
	}
	public static InvestorDAO getInvestorDAOImplementation() {
		InvestorDAO investor = new InvestorDAOImplementation();
		return investor;
	}
	public static ManagerDAO getManagerDAOImplementation() {
		ManagerDAO manager = new ManagerDAOImplementation();
		return manager;
	}
	public static AdminService getAdminServiceImplementation() {
		AdminService adminService = new AdminServiceImplementation();
		return adminService;
	}
	public static ManagerService getManagerServiceImplementation() {
		ManagerService managerService = new ManagerServiceImplementation();
		return managerService;
	}
	public static InvestorService getInvestorServiceImplementation() {
		InvestorService investorService = new InvestorServiceImplementation();
		return investorService;
	}
}
