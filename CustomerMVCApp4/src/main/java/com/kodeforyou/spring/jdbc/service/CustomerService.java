package com.kodeforyou.spring.jdbc.service;
import java.util.List;

import com.kodeforyou.spring.jdbc.beans.Customer;
import com.kodeforyou.spring.jdbc.dao.CustomerDAO;

public class CustomerService {
	private CustomerDAO customerDAO;

	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public boolean addCustomer(Customer customer) {
		return this.customerDAO.insertCustomer(customer);
		
	}

	public Customer getCustomer(int id) {
		return customerDAO.selectCustomer(id);
	}

	public boolean removeCustomer(int id) {
		return customerDAO.deleteCustomer(id);
	}

	public boolean updateCustomer(Customer customer) {
		
		return customerDAO.updateCustomer(customer);
	}
}
