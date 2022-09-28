package com.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch.entity.CustomerOutput;

public interface CustomerOutputRepo extends JpaRepository<CustomerOutput, Integer> {

}
