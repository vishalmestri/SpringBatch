package com.batch.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.batch.entity.CustomerOutput1;

public interface CustomerOutputRepo1 extends JpaRepository<CustomerOutput1, Integer> {

	@Query(
			  value = "SELECT * FROM Customer_output_1 u WHERE u.name like '%?1%'", 
			  nativeQuery = true)
			Collection<CustomerOutput1> findAllActiveUsersNative(String name);
}
