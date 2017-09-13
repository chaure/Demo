package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.DataDao;
import com.demo.model.Employee;

public class DataServiceImpl implements DataService {
	
	@Autowired
	DataDao dataDao;

	@Override
	public int insertRow(Employee employee) {
		return dataDao.insertRow(employee);
	}

	@Override
	public List<Employee> getList() {
		return dataDao.getList();
	}

	@Override
	public Employee getRowById(int id) {
		return dataDao.getRowById(id);
	}

	@Override
	public int updateRow(Employee employee) {
		return dataDao.updateRow(employee);
	}

	@Override
	public int deleteRow(int id) {
		return dataDao.deleteRow(id);
	}

	@Override
	public List<Employee> getLoginInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee validuser(String fname, String lname) {
		// TODO Auto-generated method stub
		Employee emp=dataDao.validuser(fname, lname);
		return emp;
		
	}

	

}
