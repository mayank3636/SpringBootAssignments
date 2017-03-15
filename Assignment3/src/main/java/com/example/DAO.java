package com.example;

import org.springframework.data.repository.CrudRepository;

public interface DAO extends CrudRepository<TwitterEntity, Integer> {

}
