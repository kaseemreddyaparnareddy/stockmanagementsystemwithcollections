package com.jfsfeb.stockmanagementsystem.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystem.dao.InvestorDAO;
import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.StockBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystem.validations.StockManagementSystemValidations;

public class InvestorServiceImplementation implements InvestorService {

	private InvestorDAO dao = StockManagementSystemFactory.getInvestorDAOImplementation();
	private StockManagementSystemValidations validation = new StockManagementSystemValidations();

	@Override
	public boolean investorRegistration(InvestorBean investor) {

		if (validation.validateByUserId(Integer.toString(investor.getId()))) {

			if (validation.validateByName(investor.getName())) {

				if (validation.validateByEmail(investor.getEmail())) {

					if (validation.passwordValidation(investor.getPassword())) {

						if (validation.validateByPhoneNumber(Long.toString(investor.getMobile_number()))) {

							return dao.investorRegistration(investor);
						} else {
							throw new StockManagementSystemExceptions(
									"Enter valid mobile number, should contain only 10 numbers");
						}
					} else {
						throw new StockManagementSystemExceptions(
								"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ex:Asdf@123 ");
					}
				} else {
					throw new StockManagementSystemExceptions("Enter valid emailId eg:xyz@gmail.com");
				}
			} else {
				throw new StockManagementSystemExceptions("Enter valid name, should contain only characters");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid investorId, should contain exactly 3 digits");
		}

	}

	@Override
	public boolean investorLogin(String email, String password) {

		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(password)) {

				return dao.investorLogin(email, password);
			} else {
				throw new StockManagementSystemExceptions("Enter valid password");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid emailId eg:xyz@gmail.com");
		}
	}

	public StockBean searchStock(String stockName) {

		if (validation.validateByName(stockName)) {
			return dao.searchStock(stockName);
		} else {
			throw new StockManagementSystemExceptions("Enter valid stock name, should have only characters");
		}

	}

	@Override
	public boolean buyStock(String stockName, int investorId) {
		if (validation.validateByName(stockName)) {

			if (validation.validateByUserId(Integer.toString(investorId))) {

				return dao.buyStock(stockName, investorId);
			} else {
				throw new StockManagementSystemExceptions("Enter valid investor id, should contain only 3 digits");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid stock name, should contain only characters");
		}

	}

	@Override
	public List<StockBean> getAllStocks() {

		return dao.getAllStocks();
	}

}
