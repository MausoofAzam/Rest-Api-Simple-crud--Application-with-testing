package com.snort.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.snort.app.entity.Employee;

@Repository
public interface LoginRepository extends CrudRepository<Employee, Long> {

}
