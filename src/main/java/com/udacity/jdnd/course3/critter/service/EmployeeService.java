package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.enu.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeesRepository employeesRepository;

    public EmployeeService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }


    public Employee saveEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeesRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeesRepository.save(employee);
    }

    public Employee getEmployeeById(long employeeId) {
        return employeesRepository.getOne(employeeId);
    }


    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        return employeesRepository
                .getAllEmployeesByDaysAvailable(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}
