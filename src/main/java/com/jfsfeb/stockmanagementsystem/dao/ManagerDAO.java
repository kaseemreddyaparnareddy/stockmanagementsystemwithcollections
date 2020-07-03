package com.jfsfeb.stockmanagementsystem.dao;

import com.jfsfeb.stockmanagementsystem.dto.StockBean;

public interface ManagerDAO {

	boolean managerLogin(String email, String password);

	boolean changePassword(String email, String oldPassword, String newPassword);

	boolean addStock(StockBean bean);

	boolean removeStock(int stockId);

}
