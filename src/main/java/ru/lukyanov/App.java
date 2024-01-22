package ru.lukyanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lukyanov.config.AppConfig;
import ru.lukyanov.entity.Customer;
import ru.lukyanov.repository.springbootversion.CustomerRepository;
import ru.lukyanov.service.CustomerService;

import java.util.Optional;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // BASIC CONTEXT EXTRACTION
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //CustomerService customerService = context.getBean(CustomerService.class);
        //Customer customer = customerService.get(1L);
        //System.out.println(customer);

        //SPRING BOOT RUN (NO REPO IMPL, NO SERVICE NECESSARY)
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        CustomerRepository customerRepository = context.getBean((CustomerRepository.class));
        Customer customer1 = customerRepository.findById(1L).orElseThrow();
        System.out.println(customer1);
        Customer customer2 = customerRepository.findCustomerByPassword("pass2").orElseThrow();
        System.out.println(customer2);
        customerRepository.updateLoginById("Dimon", 2L);
        Customer customer2rev = customerRepository.findById(2L).orElseThrow();
        System.out.println(customer2rev);
    }
}