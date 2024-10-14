package org.example.controller;


import org.example.dto.EmployeeDto;
import org.example.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/org")
public class OrgController {

    @Autowired
    OrganizationService orgService;

    @GetMapping("/emp")
    public  ResponseEntity<List<EmployeeDto>> allEmployees(){
        return new ResponseEntity<>(orgService.allEmployees(),HttpStatus.OK);
    }
   
    @GetMapping("/emp/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id){
        return new ResponseEntity<>(orgService.getEmployeeById(id),HttpStatus.OK);
    }

    @PostMapping ("/emp")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto empDto){
        return new ResponseEntity<>(orgService.addEmployee(empDto),HttpStatus.OK);
    }

    @PutMapping("/emp/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String id,@RequestBody EmployeeDto empDto){
        return new ResponseEntity<>(orgService.updateEmployee(id,empDto),HttpStatus.OK);
    }

    @DeleteMapping ("/emp/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable String id){
        return new ResponseEntity<>(orgService.deleteEmployee(id),HttpStatus.OK);
    }



}
