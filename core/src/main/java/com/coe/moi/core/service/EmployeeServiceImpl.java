package com.coe.moi.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coe.moi.core.dao.EmployeeRepository;
import com.coe.moi.core.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Optional<Employee> findEmployeeById(Long employeeID) {
		return employeeRepository.findById(employeeID);
	}

	@Override
	public List<Employee> findEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> createEmployee(Employee employee) {
		Employee newEmployee = employeeRepository.save(employee);
		return Optional.of(newEmployee);
	}

	@Override
	public Boolean deleteEmplyeeById(Long employeeID) {
		
		employeeRepository.deleteById(employeeID);
		return employeeRepository.existsById(employeeID);
	}

}
