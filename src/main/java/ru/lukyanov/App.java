package ru.lukyanov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lukyanov.config.AppConfig;
import ru.lukyanov.entity.Customer;
import ru.lukyanov.service.CustomerService;

public class App {
    public static void main(String[] args) {
        // СОЗДАЕМ КОНТЕКСТ
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // ПОЛУЧАЕМ БИН CustomerService
        CustomerService customerService = context.getBean(CustomerService.class);

        Customer customer = customerService.get(1L);
        System.out.println(customer);
    }
}