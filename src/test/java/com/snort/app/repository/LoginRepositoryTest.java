package com.snort.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.snort.app.entity.Employee;

//Integration Test
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // disable inMemory db, because using //
																				// installed MYSQL
class LoginRepositoryTest {

	@Autowired
	private LoginRepository loginRepository;

	@Order(5)
	@Test
	@Rollback(false) // ---it will store test data in database --
	void test_createTask() {
		Employee e1 = new Employee("Aman", "Sharma", "aman@gmail.com", "Manager");
		// will return emp with id
		e1 = loginRepository.save(e1);
		// verify employee & its id
		assertNotNull(e1);
		assertNotNull(e1.getId());
	}

	@Test
	@Order(4)
	@Rollback(false)
	void test_updateOneEmployee() {
		// first saving an emp to update the same employee
		List<Employee> empList = (List<Employee>) loginRepository.findAll();
		// will return emp with matched id
		Employee oldEmp = loginRepository.findById(empList.get(0).getId()).get();

		// updating last name only
		oldEmp.setLastName("Verma");
		// passing here modified employee details
		Employee newEmp = loginRepository.save(oldEmp);
		assertEquals(oldEmp.getLastName(), newEmp.getLastName());

	}

	@Test
	void test_findAll() {
		List<Employee> empList = (List<Employee>) loginRepository.findAll();
		// comparing list size
		assertThat(empList.size()).isGreaterThan(0);
	}

	@Test
	void test_findOneEmployee() {

		List<Employee> empList = (List<Employee>) loginRepository.findAll();
		// will return emp with matched id
		Employee find = loginRepository.findById(empList.get(0).getId()).get();
		assertNotNull(find);

	}

	@Test
	void test_deleteOne() {

		// first saving an emp to before delete the same employee details
		List<Employee> empList = (List<Employee>) loginRepository.findAll();
		// will return emp with matched id
		Employee emp = loginRepository.findById(empList.get(0).getId()).get();
		assertNotNull(emp.getId());
		// will be deleted emp with matched id
		loginRepository.deleteById(emp.getId());

	}

}
