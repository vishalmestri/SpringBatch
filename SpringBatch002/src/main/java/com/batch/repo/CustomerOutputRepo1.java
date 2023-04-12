package com.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch.entity.CustomerOutput;
import com.batch.entity.CustomerOutput1;

public interface CustomerOutputRepo1 extends JpaRepository<CustomerOutput1, Integer> {

}
