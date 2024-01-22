package ru.lukyanov.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lukyanov.entity.Customer;
import ru.lukyanov.repository.basicversion.Repo;
import ru.lukyanov.repository.springbootversion.CustomerRepository;

@Service

public class CustomerService {
    //private Repo<Customer> customerRepo;

    private final CustomerRepository customerRepo;

    @Autowired
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer get(Long id) {
        return customerRepo.findById(id).orElseThrow();
    }

    public void update(Customer customer) {
        customerRepo.save(customer);
    }

}