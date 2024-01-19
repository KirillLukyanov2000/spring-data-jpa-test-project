package ru.lukyanov.entity;

import lombok.Data;

@Data
public class Customer {
    Long id;
    String login;
    String password;
}