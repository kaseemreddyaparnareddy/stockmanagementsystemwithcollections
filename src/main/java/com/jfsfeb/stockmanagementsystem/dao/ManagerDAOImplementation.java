package com.jfsfeb.stockmanagementsystem.dao;

import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.repository.StocksManagementSystemRepository;

public class ManagerDAOImplementation implements ManagerDAO {

	@Override
	public boolean managerLogin(String email, String password) {
		for (ManagerBean managerBean : StocksManagementSystemRepository.manager) {
			if (managerBean.getEmail().equalsIgnoreCase(email) && managerBean.getPassword().equals(password)) {
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Invalid credentials");
	}

	@Override
	public boolean changePassword(String email, String oldPassword, String newPassword) {
		for (ManagerBean managerInfo : StocksManagementSystemRepository.manager) {
			if ((managerInfo.getEmail().equals(email)) && (managerInfo.getPassword().equals(oldPassword))) {
				managerInfo.setPassword(newPassword);
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Password Can't be Changed Due To Invalid Credentials");
	}

	@Override
	public boolean addStock(StockBean bean) {
		for (StockBean stockBean : StocksManagementSystemRepository.stock) {
			if (stockBean.getStockName() == bean.getStockName() && stockBean.getPrice() == bean.getPrice()
					&& stockBean.getStockId() == bean.getStockId() && stockBean.isAvailable() == bean.isAvailable()) {
				throw new StockManagementSystemExceptions(
						"Cannot add stock beacause stock with same id already existing in stocks");
			}
		}
		StocksManagementSystemRepository.stock.add(bean);
		return true;
	}

	@Override
	public boolean removeStock(int stockId) {
		for (StockBean stockInfo : StocksManagementSystemRepository.stock) {
			if (stockInfo.getStockId() == stockId) {
				StocksManagementSystemRepository.stock.remove(stockInfo);
				return true;
			}
		}

		throw new StockManagementSystemExceptions("Can't remove the stock since stockId not found");
	}

}
