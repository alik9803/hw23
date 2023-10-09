package com.example.hw23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMinSalaryByDepartment(int numberOfDepartment) {
        try {
            return employeeService.getEmployeeMap().stream()
                    .filter(employee -> employee.getDepartment() == numberOfDepartment)
                    .min(Comparator.comparingDouble(Employee::getSalary))
                    .orElseThrow(() -> new NoSuchFieldException("Сотрудник не найден"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee findMaxSalaryByDepartment(int numberOfDepartment) {
        try {
            return employeeService.getEmployeeMap().stream()
                    .filter(employee -> employee.getDepartment() == numberOfDepartment)
                    .max(Comparator.comparingDouble(Employee::getSalary))
                    .orElseThrow(() -> new NoSuchFieldException("Сотрудник не найден"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public double getTotalSalaryCostByDepartment(int department) {
        return employeeService.getEmployeeMap().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(int department) {
        return employeeService.getEmployeeMap().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getEmployeeMap().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}