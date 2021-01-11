package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetsRepository petsRepository;
    private final CustomersRepository customerRepository;

    public PetService(PetsRepository petsRepository, CustomersRepository customerRepository) {
        this.petsRepository = petsRepository;
        this.customerRepository = customerRepository;
    }

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petsRepository.save(pet);
        customer.addPet(pet);
        customerRepository.save(customer);
        return pet;
    }

    public Pet getPet(long petId) {
        return petsRepository.getOne(petId);
    }

    public List<Pet> getPets() {
        return petsRepository.findAll();
    }

    public List<Pet> getPetsByOwner(long customerId) {
        return petsRepository.getAllByCustomerId(customerId);
    }
}
