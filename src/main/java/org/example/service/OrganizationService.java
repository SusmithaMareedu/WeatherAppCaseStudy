package org.example.service;

import org.example.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface OrganizationService  {

    public List<EmployeeDto> allEmployees();

    public EmployeeDto getEmployeeById( String id);

    public EmployeeDto addEmployee(EmployeeDto empDto);

    public EmployeeDto updateEmployee(String id,EmployeeDto empDto);

    Boolean deleteEmployee( String id);


    }
