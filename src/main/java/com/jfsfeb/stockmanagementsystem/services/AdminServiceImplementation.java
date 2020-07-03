package com.jfsfeb.stockmanagementsystem.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystem.dao.AdminDAO;
import com.jfsfeb.stockmanagementsystem.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystem.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystem.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystem.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystem.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystem.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystem.validations.StockManagementSystemValidations;
import com.jfsfeb.stockmanagementsystem.validations.Validations;

public class AdminServiceImplementation implements AdminService {
	private AdminDAO dao = StockManagementSystemFactory.getAdminDAOImplementation();
	private Validations validation = new StockManagementSystemValidations();

	@Override
	public boolean adminLogin(String email, String password) {
		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(password)) {

				return dao.adminLogin(email, password);
			} else {
				throw new StockManagementSystemExceptions("Enter valid password");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid emailId eg:xyz@gmail.com");
		}
	}

	@Override
	public boolean addManager(ManagerBean managerBean) {

		if (validation.validateByName(managerBean.getName())) {

			if (validation.validateByEmail(managerBean.getEmail())) {

				if (validation.passwordValidation(managerBean.getPassword())) {

					if (validation.validateByName(managerBean.getCompanyName())) {

						return dao.addManager(managerBean);
					} else {
						throw new StockManagementSystemExceptions(
								"Enter valid company Name, should contain only characters");
					}
				} else {
					throw new StockManagementSystemExceptions(
							"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ex:Asdf@123");
				}
			} else {
				throw new StockManagementSystemExceptions("Enter valid emailId eg:xyz@gmail.com");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid name, should contain only characters");
		}
	}

	@Override
	public boolean addCompany(CompanyBean companyBean) {
		if (validation.validateByName(companyBean.getCompanyName())) {

			if (validation.validateByName(companyBean.getBranch())) {

				if (validation.validateByName(companyBean.getState())) {

					return dao.addCompany(companyBean);
				} else {
					throw new StockManagementSystemExceptions(
							"Enter valid company state, should contain only characters");
				}
			} else {
				throw new StockManagementSystemExceptions("Enter valid company branch, should contain only characters");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid company name, should contain only characters");
		}
	}

	@Override
	public List<InvestorBean> getAllInvestors() {

		return dao.getAllInvestors();
	}

	@Override
	public List<StockRequestBean> investorRequest() {

		return dao.investorRequest();
	}

	@Override
	public boolean acceptStock(String stockName, int investorId) {
		if (validation.validateByName(stockName)){
			if (validation.validateByUserId(Integer.toString(investorId))) {
				return dao.acceptStock(stockName, investorId);
			}else {
				throw new StockManagementSystemExceptions("Enter valid id, should contain only digits");
			}
			
		}else {
			throw new StockManagementSystemExceptions("Enter valid stock name, should contain only characters");
		}
		
	}

	@Override
	public boolean removeManager(int userId, String name) {
		if (validation.validateByUserId(Integer.toString(userId))) {

			if (validation.validateByName(name)) {
				
				return dao.removeManager(userId, name);
			} else {
				throw new StockManagementSystemExceptions("Enter valid manager name, should contain only characters");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid manager Id, should contain exactly 3 digits");
		}

	}

	@Override
	public boolean removeCompany(int companyId, String companyName) {
		if (validation.validateByUserId(Integer.toString(companyId))) {

			if (validation.validateByName(companyName)) {

				return dao.removeCompany(companyId, companyName);
			} else {
				throw new StockManagementSystemExceptions("Enter valid company name, should contain only characters");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid company Id, should contain exactly 3 digits");
		}
		
	}

	@Override
	public List<ManagerBean> getAllManagers() {
		
		return dao.getAllManagers();
	}

	@Override
	public List<CompanyBean> getAllCompanies() {
		
		return dao.getAllCompanies();
	}

}
