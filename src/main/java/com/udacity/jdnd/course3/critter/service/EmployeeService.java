package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.enu.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }


    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        return employeeRepository
                .getAllEmployeesByDaysAvailable(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}
