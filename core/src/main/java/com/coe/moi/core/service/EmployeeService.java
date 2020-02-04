package com.coe.moi.core.service;

import java.util.List;
import java.util.Optional;

import com.coe.moi.core.entity.Employee;

public interface EmployeeService {
	
	Optional<Employee> findEmployeeById(Long employeeID);
	
	List<Employee> findEmployees();
	
	Optional<Employee> createEmployee(Employee employee);
	
	Boolean deleteEmplyeeById(Long employeeID);
	
}
