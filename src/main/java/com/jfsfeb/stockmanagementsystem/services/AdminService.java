package com.jfsfeb.stockmanagementsystem.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystem.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockRequestBean;

public interface AdminService {
	boolean adminLogin(String email, String password);

	boolean addManager(ManagerBean managerBean);

	boolean removeManager(int userId, String name);

	boolean addCompany(CompanyBean companyBean);

	boolean removeCompany(int companyId, String companyName);

	List<InvestorBean> getAllInvestors();

	List<StockRequestBean> investorRequest();

	boolean acceptStock(String stockName, int investorId);

	List<ManagerBean> getAllManagers();

	List<CompanyBean> getAllCompanies();
}
