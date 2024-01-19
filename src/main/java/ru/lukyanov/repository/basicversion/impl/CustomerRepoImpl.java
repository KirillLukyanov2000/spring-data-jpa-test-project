package ru.lukyanov.repository.basicversion.impl;

import lombok.SneakyThrows;
import ru.lukyanov.entity.Customer;
import ru.lukyanov.repository.basicversion.ConnectionPool;
import ru.lukyanov.repository.basicversion.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class CustomerRepoImpl implements Repo<Customer>
{
    private ConnectionPool pool;

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.pool = connectionPool;
    }

    public static final String GET_BY_ID = "SELECT id, login, password FROM customer WHERE id=?";
    public static final String UPDATE = "UPDATE customer SET login=?, password=? WHERE id=?";

    @Override
    @SneakyThrows
    public Optional<Customer> getById(Long id) {
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                return Optional.of(customer);
            } else {
                throw new SQLException();
            }
        }
    }

    @Override
    @SneakyThrows
    public void update(Customer entity) {
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        }
    }
}