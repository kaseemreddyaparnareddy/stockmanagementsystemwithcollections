package com.jfsfeb.stockmanagementsystem.dao;

import java.util.List;

import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;

public interface InvestorDAO {
	boolean investorRegistration(InvestorBean investor);

	boolean investorLogin(String email, String password);

	public StockBean searchStock(String stockName);

	boolean buyStock(String stockName, int investorId);

	List<StockBean> getAllStocks();

}
