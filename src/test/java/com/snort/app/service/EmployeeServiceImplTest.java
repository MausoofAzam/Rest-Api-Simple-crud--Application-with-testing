package com.snort.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.snort.app.entity.Employee;
import com.snort.app.repository.LoginRepository;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private LoginRepository loginRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Test
	void test_findAll() {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(101L, "Aman", "Sharma", "aman@gmail.com", "Manager"));
		empList.add(new Employee(102L, "Suman", "Kapoor", "suman@gmail.com", "Clerk"));
		when(loginRepository.findAll()).thenReturn(empList);
		assertThat(empList.size()).isGreaterThan(0);
	}

	@Test
	void test_findOneEmployee() {
		Optional<Employee> e1 = Optional.ofNullable(new Employee(101L, "Aman", "Sharma", "aman@gmail.com", "Manager"));
		when(loginRepository.findById(Mockito.anyLong())).thenReturn(e1);
		assertNotNull(e1);
	}

	@Test
	void test_createTask() {
		Long id = 101L;
		Employee e1 = new Employee(id, "Aman", "Sharma", "aman@gmail.com", "Manager");
		when(loginRepository.save(Mockito.any(Employee.class))).thenReturn(e1);
		assertNotNull(e1);
	}

	@Test
	void test_updateOneEmployee() {
		Optional<Employee> e1 = Optional.ofNullable(new Employee(101L, "Aman", "Sharma", "aman@gmail.com", "Manager"));
		when(loginRepository.findById(Mockito.anyLong())).thenReturn(e1);
		// set new value
		e1.get().setLastName("Kapoor");
		when(loginRepository.save(Mockito.any(Employee.class))).thenReturn(e1.get());
		assertNotNull(e1);
	}

	@Test
	void test_deleteOne() {
		doNothing().when(loginRepository).deleteById(Mockito.anyLong());
	}

}
