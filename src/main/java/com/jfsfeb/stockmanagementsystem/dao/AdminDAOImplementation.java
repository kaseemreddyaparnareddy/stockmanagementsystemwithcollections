package com.jfsfeb.stockmanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.jfsfeb.stockmanagementsystem.dto.AdminBean;
import com.jfsfeb.stockmanagementsystem.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.repository.StocksManagementSystemRepository;

public class AdminDAOImplementation implements AdminDAO {

	@Override
	public boolean adminLogin(String email, String password) {
		for (AdminBean adminBean : StocksManagementSystemRepository.admin) {
			if (adminBean.getEmail().equalsIgnoreCase(email) && adminBean.getPassword().equals(password)) {
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Invalid admin credentials");
	}

	@Override
	public boolean addManager(ManagerBean managerBean) {
		for (ManagerBean managerInfo : StocksManagementSystemRepository.manager) {
			if ((managerInfo.getUserId() == managerBean.getUserId())
					|| managerInfo.getEmail().equalsIgnoreCase(managerBean.getEmail())) {
				throw new StockManagementSystemExceptions("User with same EmailId is already exist");

			}

		}
		StocksManagementSystemRepository.manager.add(managerBean);
		return true;
	}

	@Override
	public boolean addCompany(CompanyBean companyBean) {
		for (CompanyBean companyInfo : StocksManagementSystemRepository.company) {
			if ((companyInfo.getCompanyName() == companyBean.getCompanyName())
					|| companyInfo.getBranch().equalsIgnoreCase(companyBean.getBranch())) {
				throw new StockManagementSystemExceptions("User with same EmailId is already exist");

			}

		}
		StocksManagementSystemRepository.company.add(companyBean);
		return true;
	}

	@Override
	public List<InvestorBean> getAllInvestors() {
		List<InvestorBean> investorList = new ArrayList<InvestorBean>();
		for (InvestorBean investors : StocksManagementSystemRepository.investor) {
			investors.getId();
			investors.getName();
			investors.getNumberOfStocks();
			investors.getEmail();
			investorList.add(investors);

		}
		if (investorList.isEmpty()) {
			throw new StockManagementSystemExceptions("No investor found");
		}
		return investorList;
	}

	@Override
	public List<StockRequestBean> investorRequest() {
		List<StockRequestBean> reqInfo = new ArrayList<StockRequestBean>();
		for (StockRequestBean requestInfo : StocksManagementSystemRepository.stockRequest) {
			requestInfo.getInvestorName();
			requestInfo.getInvestorId();
			reqInfo.add(requestInfo);
		}
		if (reqInfo.isEmpty()) {
			throw new StockManagementSystemExceptions("No Requests are found");
		}

		return reqInfo;
	}

	@Override
	public boolean acceptStock(String stockName, int investorId) {
		InvestorBean investorBean = new InvestorBean();
		StockBean stockBean = new StockBean();
		StockRequestBean requestInfo = new StockRequestBean();
		int noOfStocks = 0;
		boolean isValidRequest = false;

		for (StockRequestBean info : StocksManagementSystemRepository.stockRequest) {
			if (info.getInvestorId() == investorId) {
				if (info.getStockName().equals(stockName)) {
					isValidRequest = true;
					requestInfo = info;
				}
			}
		}

		if (isValidRequest) {
			for (StockBean stock : StocksManagementSystemRepository.stock) {
				if (stock.getStockName().equals(stockName)) {
					stockBean = stock;
				}
			}

			for (InvestorBean investor : StocksManagementSystemRepository.investor) {
				if (investor.getId() == investorId) {
					investorBean = investor;
					noOfStocks = investor.getNumberOfStocks();
				}
			}

			if (noOfStocks < 3) {
				stockBean.setAvailable(false);
				noOfStocks++;
				investorBean.getNumberOfStocks();
				return true;
			} else {
				StocksManagementSystemRepository.stockRequest.remove(requestInfo);
				throw new StockManagementSystemExceptions(
						" Stock cannot be accepted as it exceeds maximum stocks limit");
			}

		} else
			throw new StockManagementSystemExceptions(" Can't be accepted due to invalid");
	}

	@Override
	public boolean removeManager(int userId, String name) {
		for (ManagerBean managerInfo : StocksManagementSystemRepository.manager) {
			if (managerInfo.getUserId() == userId && managerInfo.getName().equals(name)) {
				StocksManagementSystemRepository.manager.remove(managerInfo);
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Can't remove the manager since manager not found");
	}

	@Override
	public boolean removeCompany(int companyId, String companyName) {
		for (CompanyBean companyInfo : StocksManagementSystemRepository.company) {
			if (companyInfo.getCompanyId() == companyId && companyInfo.getCompanyName().equals(companyName)) {
				StocksManagementSystemRepository.company.remove(companyInfo);
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Can't remove the company since company name not found");
	}

	@Override
	public List<ManagerBean> getAllManagers() {
		List<ManagerBean> managerList = new ArrayList<ManagerBean>();
		for (ManagerBean managers : StocksManagementSystemRepository.manager) {
			managers.getUserId();
			managers.getName();
			managers.getCompanyName();
			managers.getEmail();
			managers.getDesignation();
			managerList.add(managers);
		}
		if (managerList.isEmpty()) {
			throw new StockManagementSystemExceptions("No Manager found");
		}
		return managerList;
	}

	@Override
	public List<CompanyBean> getAllCompanies() {
		List<CompanyBean> companyList = new ArrayList<CompanyBean>();
		for (CompanyBean company : StocksManagementSystemRepository.company) {
			company.getBranch();
			company.getCompanyId();
			company.getCompanyName();
			company.getState();
			companyList.add(company);

		}
		if (companyList.isEmpty()) {
			throw new StockManagementSystemExceptions("No company found");
		}
		return companyList;
	}
}
