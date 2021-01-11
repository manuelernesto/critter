package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import com.udacity.jdnd.course3.critter.repository.SchedulesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final SchedulesRepository schedulesRepository;
    private final PetsRepository petsRepository;
    private final EmployeesRepository employeesRepository;
    private final CustomersRepository customerRepository;

    public ScheduleService(SchedulesRepository schedulesRepository, PetsRepository petsRepository, EmployeesRepository employeesRepository, CustomersRepository customerRepository) {
        this.schedulesRepository = schedulesRepository;
        this.petsRepository = petsRepository;
        this.employeesRepository = employeesRepository;
        this.customerRepository = customerRepository;
    }

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeesRepository.findAllById(employeeIds);
        List<Pet> pets = petsRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return schedulesRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    public List<Schedule> getAllSchedulesForPet(long petId) {
        Pet pet = petsRepository.getOne(petId);
        return schedulesRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> getAllSchedulesForEmployee(long employeeId) {
        Employee employee = employeesRepository.getOne(employeeId);
        return schedulesRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> getAllSchedulesForCustomer(long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return schedulesRepository.getAllByPetsIn(customer.getPets());
    }


}
