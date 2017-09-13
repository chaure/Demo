package com.demo.service;

import java.util.List;

import com.demo.model.Employee;

public interface DataService {
	public int insertRow(Employee employee);

	public List<Employee> getList();

	public Employee getRowById(int id);

	public int updateRow(Employee employee);

	public int deleteRow(int id);

	public List<Employee> getLoginInfo();

	public Employee validuser(String fname, String lname);

}
