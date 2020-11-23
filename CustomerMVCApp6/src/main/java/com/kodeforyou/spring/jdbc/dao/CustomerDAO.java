package com.kodeforyou.spring.jdbc.dao;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kodeforyou.spring.jdbc.beans.Customer;

public class CustomerDAO  {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Customer> getAllCustomers() {
		String sql = "select * from customers";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	public boolean insertCustomer(Customer customer) {
		String sql = "insert into customers (name,age,phoneNumber) values(?,?,?)";
		int count = jdbcTemplate.update(sql,
				new Object[] { customer.getName(), customer.getAge(), customer.getPhoneNumber() },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.BIGINT });

		System.out.println("Number of Rows Inserted: " + count);
		return count != 0;
	}
	
	public Customer selectCustomer(int id) {
		String sql = "select * from customers where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { new Integer(id) },
				new BeanPropertyRowMapper<Customer>(Customer.class));
	}
	
	public Customer selectCustomer(String name) {
		String sql = "select * from customers where name=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { name },
				new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	public Customer selectCustomer(long phonenumber) {
		String sql = "select * from customers where phonenumber=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { new Long(phonenumber) },
				new BeanPropertyRowMapper<Customer>(Customer.class));
	}
	
	public boolean deleteCustomer(int id) {
		String sql = "DELETE FROM customers WHERE id =?";
		int count = jdbcTemplate.update(sql, id);
		return count != 0;
	}

	public boolean updateCustomer(Customer customer) {
		String sql = "UPDATE   customers SET  name = ?, age = ?, phonenum = ?,WHERE id=?";
		int count = jdbcTemplate.update(sql, customer.getName(), customer.getAge(), customer.getPhoneNumber(),
				customer.getId());
		return count != 0;
	}
}
