package com.jfsfeb.stockmanagementsystem.services;

import com.jfsfeb.stockmanagementsystem.dto.StockBean;

public interface ManagerService {

	boolean managerLogin(String email, String password);

	boolean changePassword(String email, String oldPassword, String newPassword);

	boolean addStock(StockBean bean);

	boolean removeStock(int stockId);

}