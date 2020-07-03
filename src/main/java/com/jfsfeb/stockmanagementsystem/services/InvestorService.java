package com.jfsfeb.stockmanagementsystem.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;

public interface InvestorService {
	boolean investorRegistration(InvestorBean investor);

	boolean investorLogin(String email, String password);

	public StockBean searchStock(String stockName);

	boolean buyStock(String stockName, int investorId);

	List<StockBean> getAllStocks();

}
