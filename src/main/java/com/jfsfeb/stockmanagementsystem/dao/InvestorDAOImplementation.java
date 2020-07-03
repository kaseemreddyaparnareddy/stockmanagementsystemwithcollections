package com.jfsfeb.stockmanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.repository.StocksManagementSystemRepository;

public class InvestorDAOImplementation implements InvestorDAO {

	@Override
	public boolean investorLogin(String email, String password) {
		for (InvestorBean investorBean : StocksManagementSystemRepository.investor) {
			if (investorBean.getEmail().equalsIgnoreCase(email) && investorBean.getPassword().equals(password)) {
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Invalid credentials");
	}

	@Override
	public StockBean searchStock(String stockName) {
		for (StockBean stockBeans : StocksManagementSystemRepository.stock) {
			if (stockBeans.getStockName().equalsIgnoreCase(stockName)) {
				return stockBeans;
			}
		}
		throw new StockManagementSystemExceptions("stock is not present");
	}

	@Override
	public boolean buyStock(String stockName, int investorId) {
		StockRequestBean stockRequestInfo = new StockRequestBean();
		boolean isRequestExists = false;

		for (StockRequestBean stocksInfo : StocksManagementSystemRepository.stockRequest) {
			if (stockName == stocksInfo.getStockName()) {
				isRequestExists = true;
			}
		}

		if (!isRequestExists) {
			for (InvestorBean investorBean : StocksManagementSystemRepository.investor) {
				if (investorId == investorBean.getId()) {
					for (StockBean stockBean : StocksManagementSystemRepository.stock) {
						if ((stockBean.getStockName().equals(stockName)) && (stockBean.isAvailable() == true)) {
							stockRequestInfo.setStockName(stockName);
							stockRequestInfo.setInvestorId(investorId);
							StocksManagementSystemRepository.stockRequest.add(stockRequestInfo);
							return true;
						}
					}
				}
			}
		}
		throw new StockManagementSystemExceptions("Invalid stock");
	}

	@Override
	public List<StockBean> getAllStocks() {
		List<StockBean> stocksList = new ArrayList<StockBean>();
		for (StockBean stocks : StocksManagementSystemRepository.stock) {
			stocks.getStockId();
			stocks.getStockName();
			stocks.getPrice();
			stocksList.add(stocks);

		}
		if (stocksList.isEmpty()) {
			throw new StockManagementSystemExceptions("No stocks found");
		}
		return stocksList;
	}

	@Override
	public boolean investorRegistration(InvestorBean investor) {
		for (InvestorBean investorBeans : StocksManagementSystemRepository.investor) {
			if (investorBeans.getId() == investor.getId() || investorBeans.getEmail().equals(investor.getEmail())) {
				throw new StockManagementSystemExceptions("Can't register investor id or Email already exists");
			}
		}
		StocksManagementSystemRepository.investor.add(investor);
		return true;
	}

}
