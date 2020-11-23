package com.kodeforyou.spring.jdbc.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.kodeforyou.spring.jdbc.beans.Customer;
import com.kodeforyou.spring.jdbc.service.CustomerService;

public class CustomerController extends MultiActionController {

	private CustomerService customerService;
	private String successView;
	private String errorView;

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public ModelAndView getAllCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Customer> customers = customerService.getAllCustomers();
		ModelAndView modelAndView = null;
		if (customers == null || customers.isEmpty())
			modelAndView = new ModelAndView(errorView, "customers", customers);
		else
			modelAndView = new ModelAndView(successView, "customers", customers);
		return modelAndView;
	}

	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		Customer customer = new Customer();
		customer.setPhoneNumber(phoneNumber);
		customer.setAge(age);
		customer.setName(name);
		boolean status = customerService.addCustomer(customer);
		if (status)
			return new ModelAndView(successView, "msg", "customer added successfully");
		else
			return new ModelAndView(errorView, "msg", "customer not added");
	}

	public ModelAndView getCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerService.getCustomer(id);
		if (customer != null)
			return new ModelAndView(successView, "customers", customer);
		else
			return new ModelAndView(errorView, "msg", "no such customer");
	}

	public ModelAndView removeCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		if (customerService.removeCustomer(id))
			return new ModelAndView(successView, "msg", "customer deleted successfully");
		else
			return new ModelAndView(errorView, "msg", "customer not deleted");
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id =  Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		Customer customer = new Customer();
		customer.setId(id);
		customer.setPhoneNumber(phoneNumber);
		customer.setAge(age);
		customer.setName(name);
		if (customerService.updateCustomer(customer))
			return new ModelAndView(successView, "msg", "customer updated successfully");
		else
			return new ModelAndView(errorView, "msg", "customer not updated");	}

}

