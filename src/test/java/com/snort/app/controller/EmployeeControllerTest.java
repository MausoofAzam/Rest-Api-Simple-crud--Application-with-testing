package com.snort.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snort.app.entity.Employee;
import com.snort.app.service.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void test_findAll() throws Exception {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(101L, "Aman", "Sharma", "aman@gmail.com", "Manager"));
		empList.add(new Employee(102L, "Suman", "Kapoor", "suman@gmail.com", "Clerk"));
		when(employeeService.findAll()).thenReturn(empList);
		assertNotNull(empList);

		// calling rest api
		ResultActions response = mockMvc.perform(get("/emp/all").contentType(MediaType.APPLICATION_JSON));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(empList.size())));
	}

	@Test
	void test_findOneEmployee() throws Exception {
		Long id = 101L;
		Employee e1 = new Employee(id, "Aman", "Sharma", "aman@gmail.com", "Manager");

		when(employeeService.findOneEmployee(id)).thenReturn(e1);
		assertNotNull(e1);

		// calling rest api
		ResultActions response = mockMvc.perform(get("/emp/{id}", id).contentType(MediaType.APPLICATION_JSON));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.firstName", is(e1.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(e1.getLastName())))
				.andExpect(jsonPath("$.email", is(e1.getEmail())));
	}

	@Test
	void test_deleteOne() throws Exception {
		Long id = 101L;
		String msg = "Employee with id -->" + id + " successfully deleted";
		when(employeeService.deleteOne(Mockito.anyLong())).thenReturn(msg);

		// calling rest api
		ResultActions response = mockMvc
				.perform(delete("/emp/delete/{id}", id).contentType(MediaType.APPLICATION_JSON));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());
	}

	@Test
	void test_createTask() throws JsonProcessingException, Exception {
		Long id = 101L;
		Employee e1 = new Employee(id, "Aman", "Sharma", "aman@gmail.com", "Manager");

		when(employeeService.createTask(Mockito.any(Employee.class))).thenReturn(e1);

		// calling rest api
		ResultActions response = mockMvc.perform(post("/emp/create").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(e1)));

		// then - verify the output
		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is(e1.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(e1.getLastName())))
				.andExpect(jsonPath("$.email", is(e1.getEmail())));

	}

	@Test
	void test_updateOneEmployee() throws JsonProcessingException, Exception {
		Long id = 102L;
		Employee oldEmp = new Employee(102L, "Suman", "Kapoor", "suman@gmail.com", "Clerk");
		Employee e1 = new Employee(102L, "Suman", "Kharatri", "suman013@gmail.com", "Manager");

		// finding existing employee details having id 102 before update
		when(employeeService.findOneEmployee(id)).thenReturn(oldEmp);

		// now update the same employee details
		when(employeeService.updateOneEmployee(Mockito.anyLong(), Mockito.any(Employee.class))).thenReturn(e1);

		ResultActions response = mockMvc.perform(put("/update/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(e1)));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.firstName", is(e1.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(e1.getLastName())))
				.andExpect(jsonPath("$.email", is(e1.getEmail())));

	}

}
