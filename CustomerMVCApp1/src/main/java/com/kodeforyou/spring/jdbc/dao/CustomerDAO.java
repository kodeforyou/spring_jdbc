package com.kodeforyou.spring.jdbc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.kodeforyou.spring.jdbc.beans.Customer;
import com.kodeforyou.spring.jdbc.dbconfig.DBConfig;

public class CustomerDAO implements InitializingBean {

	private DBConfig dbConfig;

	public void setDbConfig(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}
	

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		// db logic

		String sql = "select * from customers";

		Connection con = null;
		try {
			try {
				System.out.println("connecting to db....");

				con = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword());
				System.out.println("connection establised");
				Statement st = con.createStatement();
				System.out.println("getting customer deatils");
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					Customer customer = new Customer();
					System.out.println("");
					customer.setId(rs.getInt(1));
					customer.setName(rs.getString(2));
					customer.setAge(rs.getInt(3));
					customer.setPhoneNumber(rs.getLong(4));
					customers.add(customer);
				}

			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return customers;
	}

	public boolean insertCustomer(Customer customer) {
		String sql = "insert into customers (name,age,phoneNumber) values(?,?,?)";

		Connection con = null;
		int count = 0;
		try {
			try {
				System.out.println("connecting to db....");

				con = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword());

				System.out.println("connection establised");
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, customer.getName());
				ps.setInt(2, customer.getAge());
				ps.setLong(3, customer.getPhoneNumber());
				System.out.println("inserting data in to db table customers");
				count = ps.executeUpdate();
				System.out.println(count + " rows inserted");
				con.commit();
			} catch (SQLException sqle) {
				if (con != null)
					con.rollback();
				throw sqle;
			} finally {
				if (con != null) {

					con.close();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return count != 0;

	}

	public Customer selectCustomer(int id) {

		String sql = "select * from customers where id=?";

		Connection con = null;
		Customer customer = null;
		try {
			try {
				System.out.println("connecting to db....");

				con = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword());
				System.out.println("connection establised");
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);

				System.out.println("getting customer deatils whose id is:" + id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					customer = new Customer();
					customer.setId(rs.getInt(1));
					customer.setName(rs.getString(2));
					customer.setAge(rs.getInt(3));
					customer.setPhoneNumber(rs.getLong(4));
				}

			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return customer;
	}

	public boolean deleteCustomer(int id) {
		String sql = "DELETE FROM customers WHERE id =?";
		int count = 0;
		Connection con = null;
		try {
			try {
				System.out.println("connecting to db....");

				con = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword());
				System.out.println("connection establised");
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				System.out.println("deleting data in to db table customers");
				count = ps.executeUpdate();
				System.out.println(count + " rows deleted");

			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return count != 0;
	}

	public boolean updateCustomer(Customer customer) {
		String sql = "UPDATE   customers SET  name = ?, age = ?, phonenum = ?,WHERE id=?";

		Connection con = null;
		int count = 0;
		try {
			try {
				System.out.println("connecting to db....");

				con = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUser(), dbConfig.getDbPassword());

				System.out.println("connection establised");
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, customer.getName());
				ps.setInt(2, customer.getAge());
				ps.setLong(3, customer.getPhoneNumber());
				ps.setInt(4, customer.getId());
				System.out.println("updating data in to db table customers");
				count = ps.executeUpdate();
				System.out.println(count + " rows updated");
				con.commit();
			} catch (SQLException sqle) {
				if (con != null)
					con.rollback();
				throw sqle;
			} finally {
				if (con != null) {

					con.close();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return count != 0;

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			Class.forName(dbConfig.getDriverClass());
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
}
