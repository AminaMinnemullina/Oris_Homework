package oop.repositories;

import oop.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    void saveAll(List<User> users) throws SQLException;

    List<User> findAllByAgeGreaterThan(Integer age) throws SQLException;

    List<User> findAllByAge(Integer age);
}