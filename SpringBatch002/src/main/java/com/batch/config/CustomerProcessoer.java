package com.batch.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.batch.item.ItemProcessor;

import com.batch.entity.CustomerInput;
import com.batch.entity.CustomerOutput;
import com.batch.entity.CustomerOutput1;
import com.batch.pojo.CustomerOutputWriter;

public class CustomerProcessoer implements ItemProcessor<CustomerInput, CustomerOutputWriter> {

	@Override
	public CustomerOutputWriter process(CustomerInput input) throws Exception {
		Random r=new Random();
		System.out.println("CustomerProcessoer--|Thread="+Thread.currentThread().getName());
		List<CustomerOutput> list=new ArrayList<CustomerOutput>();
		CustomerOutput output=new CustomerOutput();
		//output.setId(input.getId());
		output.setName(input.getName()+"|"+input.getId()+r.nextInt(1000));
		output.setGender(input.getGender());
		
		CustomerOutput output1=new CustomerOutput();
		//output.setId(input.getId());
		output1.setName(input.getName()+"|"+input.getId()+r.nextInt(1000));
		output1.setGender(input.getGender());
		
		list.add(output);
		list.add(output1);
		
		
		
		
		List<CustomerOutput1> list_1=new ArrayList<CustomerOutput1>();
		CustomerOutput1 output_1=new CustomerOutput1();
		//output.setId(input.getId());
		output_1.setName(input.getName()+"|"+input.getId()+r.nextInt(1000));
		output_1.setGender(input.getGender());
		
		CustomerOutput1 output_2=new CustomerOutput1();
		//output.setId(input.getId());
		output_2.setName(input.getName()+"|"+input.getId()+r.nextInt(1000));
		output_2.setGender(input.getGender());
		
		list_1.add(output_1);
		list_1.add(output_2);
		
		
		CustomerOutputWriter cust=new CustomerOutputWriter();
		cust.setList(list);
		cust.setList1(list_1);
		
		
		return cust;
	}
	

}
