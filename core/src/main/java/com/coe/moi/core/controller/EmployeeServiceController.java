package com.coe.moi.core.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coe.moi.core.entity.Employee;
import com.coe.moi.core.service.EmployeeService;

@RestController
public class EmployeeServiceController {
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employees/id/{id}")
	public ResponseEntity<?>  findEmployeeById(@PathVariable("id") Long employeeID){
		Optional<Employee> employee=service.findEmployeeById(employeeID);
		return new ResponseEntity<>(employee,(employee.isPresent())?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<?>  findEmployees(){
		List<Employee> employees=service.findEmployees();
		return new ResponseEntity<>(employees,((employees!=null) && (employees.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/employees/new")
	public ResponseEntity<?>  createEmployee(@RequestBody Employee employee){
		Optional<Employee> newEmployee=service.createEmployee(employee);
		return new ResponseEntity<>(employee,(newEmployee.isPresent())?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@PutMapping("/employees/remove/id/{id}")
	public ResponseEntity<?>  deleteEmplyeeById(@PathVariable("id") Long employeeID){
		Boolean status = service.deleteEmplyeeById(employeeID);
		return new ResponseEntity<>(status,(status)?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
	}
	
}
