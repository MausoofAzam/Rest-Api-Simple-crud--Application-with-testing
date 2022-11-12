package com.snort.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snort.app.entity.Employee;
import com.snort.app.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/emp/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createTask(@RequestBody Employee employee) {
		log.info("Spring Crud Operations : Create Task Executed");
		return employeeService.createTask(employee);

	}

	@GetMapping("/emp/all")
	public List<Employee> findAll() {
		log.info("Spring Crud Operations : findAll Task Executed");
		return employeeService.findAll();

	}

	@GetMapping("emp/{id}")
	public Employee findOneEmployee(@PathVariable Long id) {
		log.info("Spring Crud-Operation : find one Task executed");
		return employeeService.findOneEmployee(id);
	}

	@DeleteMapping("/emp/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		log.info("Spring Crud operations : Delete one task executed");
		return employeeService.deleteOne(id);
	}

	@PutMapping("/update/{id}")
	public Employee updateOneEmployee(@PathVariable Long id, @RequestBody Employee newEmp) {
		return employeeService.updateOneEmployee(id, newEmp);

	}
}
