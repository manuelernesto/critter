package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomersRepository customerRepository;
    private final PetsRepository petsRepository;

    public CustomerService(CustomersRepository customerRepository, PetsRepository petsRepository) {
        this.customerRepository = customerRepository;
        this.petsRepository = petsRepository;
    }

    public Customer saveCustomer(Customer customer, List<Long> petsId) {
        List<Pet> pets = new ArrayList<>();
        if (petsId != null && !petsId.isEmpty()) {
            pets = petsId.stream().map(petsRepository::getOne).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public Customer getCustomerByPet(long petId) {
        return petsRepository.getOne(petId).getCustomer();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
