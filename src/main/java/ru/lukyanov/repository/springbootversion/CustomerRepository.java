package ru.lukyanov.repository.springbootversion;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lukyanov.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    Optional<Customer> findById(Long aLong);

    Optional<Customer> findCustomerByLogin(String login);

    Optional<Customer> findCustomerByPassword(String password);

    Optional<Customer> findByLoginAndPassword(String login, String password);

    Optional<Customer> findAllByLogin(String login);

    @Transactional
    @Modifying
    @Query("update Customer c set c.login = ?1 where c.id = ?2")
    void updateLoginById(String login, Long id);

    long countByPasswordIgnoreCase(String password);

    @Override
    <S extends Customer> S save(S entity);
}
