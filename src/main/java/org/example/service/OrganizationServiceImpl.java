package org.example.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.example.dto.EmployeeDto;
import org.example.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService{


    @Autowired
    DynamoDBMapper dynamoDBMapper;

    ArrayList<EmployeeDto> empList=new ArrayList<>();

    @Override
    public List<EmployeeDto> allEmployees() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<EmployeeEntity> employees = dynamoDBMapper.scan(EmployeeEntity.class, scanExpression);
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());

    }



    @Override
    public EmployeeDto getEmployeeById(String id) {
        Optional<EmployeeDto> empDto= Optional.of(new EmployeeDto());
       empDto=empList.stream().filter(EmployeeDto->EmployeeDto.getId()==id).findFirst();
        return empDto.orElse(null);
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto empDto) {

        EmployeeEntity emp = new EmployeeEntity();
        BeanUtils.copyProperties(empDto, emp);
        dynamoDBMapper.save(emp);

        return convertToDTO(emp);
    }

    @Override
    public EmployeeDto updateEmployee(String id, EmployeeDto empDto) {
        EmployeeEntity emp = dynamoDBMapper.load(EmployeeEntity.class, id);
        BeanUtils.copyProperties(empDto, emp);
        dynamoDBMapper.save(emp);
        return convertToDTO(emp);
    }

    @Override
    public Boolean deleteEmployee(String id) {
        EmployeeEntity emp = dynamoDBMapper.load(EmployeeEntity.class, id);
        if (emp != null) {
            dynamoDBMapper.delete(emp);
            return true;
        }
        return false;
    }

    private EmployeeDto convertToDTO(EmployeeEntity emp) {

        EmployeeDto empDto = new   EmployeeDto();
        BeanUtils.copyProperties(emp, empDto);
        return empDto;
    }
}
