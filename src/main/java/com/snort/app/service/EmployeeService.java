package com.snort.app.service;

import java.util.List;

import com.snort.app.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee updateOneEmployee(Long id, Employee newEmp);

	Employee createTask(Employee employee);

	Employee findOneEmployee(Long id);

	String deleteOne(Long id);

}
