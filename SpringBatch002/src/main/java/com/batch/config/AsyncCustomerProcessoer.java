package com.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.batch.entity.CustomerInput;
import com.batch.entity.CustomerOutput;

public class AsyncCustomerProcessoer implements   ItemProcessor<CustomerInput, CustomerOutput> {

	
	
	@Override
	public CustomerOutput process(CustomerInput input) throws Exception {
		System.out.println("CustomerProcessoer--|Thread="+Thread.currentThread().getName());
		CustomerOutput output=new CustomerOutput();
		output.setId(input.getId());
		output.setName(input.getName());
		output.setGender(input.getGender());
		return output;
	}
	

}
