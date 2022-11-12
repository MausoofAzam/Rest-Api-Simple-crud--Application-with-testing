package com.snort.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snort.app.entity.Employee;
import com.snort.app.repository.LoginRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Employee createTask(Employee employee) {

		return loginRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {

		return (List<Employee>) loginRepository.findAll();
	}

	@Override
	public Employee findOneEmployee(Long id) {

		Optional<Employee> employee = loginRepository.findById(id);
		return employee.orElse(new Employee());
	}

	@Override
	public String deleteOne(Long id) {
		try {
			loginRepository.deleteById(id);
			return "Employee with id -->" + id + " successfully deleted";
		} catch (Exception e) {
			return "Employee with id -->" + id + " failed to delete";
		}
	}

	@Override
	public Employee updateOneEmployee(Long id, Employee newEmp) {
		newEmp.setId(id);
		if (loginRepository.existsById(id)) {
			return loginRepository.save(newEmp);
		}
		return new Employee();
	}

}
