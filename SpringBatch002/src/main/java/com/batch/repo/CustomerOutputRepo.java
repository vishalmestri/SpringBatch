package com.batch.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.batch.entity.CustomerOutput;

public interface CustomerOutputRepo extends JpaRepository<CustomerOutput, Integer> {

	
	@Query(
			  value = "SELECT * FROM Customer_output u WHERE u.name like '%?1%'", 
			  nativeQuery = true)
			public Collection<CustomerOutput> findAllActiveUsersNative(String name);
}
