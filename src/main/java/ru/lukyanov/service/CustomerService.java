package ru.lukyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lukyanov.entity.Customer;
import ru.lukyanov.repository.basicversion.Repo;

@Service
public class CustomerService {
    private Repo<Customer> customerRepo;

    @Autowired
    public void setCustomerRepo(Repo<Customer> customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer get(Long id) {
        return customerRepo.getById(id).orElseThrow();
    }

    public void update(Customer customer) {
        customerRepo.update(customer);
    }


}