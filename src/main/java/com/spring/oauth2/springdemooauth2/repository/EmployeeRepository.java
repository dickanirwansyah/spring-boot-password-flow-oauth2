package com.spring.oauth2.springdemooauth2.repository;

import com.spring.oauth2.springdemooauth2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
