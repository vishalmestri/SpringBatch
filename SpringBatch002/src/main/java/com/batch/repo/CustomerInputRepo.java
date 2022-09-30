package com.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch.entity.CustomerInput;

public interface CustomerInputRepo extends JpaRepository<CustomerInput, Integer> {

}
