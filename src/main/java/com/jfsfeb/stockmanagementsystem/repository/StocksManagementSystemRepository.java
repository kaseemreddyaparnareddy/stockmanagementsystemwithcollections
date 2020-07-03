package com.jfsfeb.stockmanagementsystem.repository;

import java.util.ArrayList;
import java.util.List;

import com.jfsfeb.stockmanagementsystem.dto.AdminBean;
import com.jfsfeb.stockmanagementsystem.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.dto.StockRequestBean;

public class StocksManagementSystemRepository {
	public static final List<AdminBean> admin = new ArrayList<AdminBean>();
	public static final List<CompanyBean> company = new ArrayList<CompanyBean>();
	public static final List<InvestorBean> investor = new ArrayList<InvestorBean>();
	public static final List<ManagerBean> manager = new ArrayList<ManagerBean>();
	public static final List<StockBean> stock = new ArrayList<StockBean>();
	public static final List<StockRequestBean> stockRequest = new ArrayList<StockRequestBean>();

	public static void addToDatabase() {
		AdminBean admin1 = new AdminBean();
		admin1.setUserId(100);
		admin1.setEmail("aparna@gmail.com");
		admin1.setName("aparna");
		admin1.setPassword("Appu@1234");
		admin.add(admin1);
		CompanyBean company1 = new CompanyBean();
		company1.setCompanyId(123);
		company1.setBranch("Hyderbad");
		company1.setCompanyName("capgemini");
		company1.setState("Telangana");
		company.add(company1);
		InvestorBean investor1 = new InvestorBean();
		investor1.setAccount_number(1236547890);
		investor1.setEmail("abhi@gmail.com");
		investor1.setId(123);
		investor1.setName("Abhi");
		investor1.setNumberOfStocks(2);
		investor1.setPassword("Abhi@123");
		investor1.setMobile_number(9874563210l);
		investor.add(investor1);
		ManagerBean manager1 = new ManagerBean();
		manager1.setCompanyName("capgemini");
		manager1.setDesignation("Manager");
		manager1.setEmail("mohammed@gmail.com");
		manager1.setName("Mohammed");
		manager1.setPassword("Ishqbasha@123");
		manager1.setUserId(123);
		manager.add(manager1);
		StockBean stocks = new StockBean();
		stocks.setAvailable(true);
		stocks.setPrice(3000);
		stocks.setStockId(1234);
		stocks.setStockName("tcs");
		stock.add(stocks);
	}
}
