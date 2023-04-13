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
		//--------------------------------------------------------------
		
		List<CustomerOutput> list=new ArrayList<CustomerOutput>();
		CustomerOutput output=new CustomerOutput();
		//output.setId(input.getId());
		output.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
		output.setGender(input.getGender());
		
		CustomerOutput output1=new CustomerOutput();
		//output.setId(input.getId());
		output1.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
		output1.setGender(input.getGender());
		
		list.add(output);
		list.add(output1);
		
		//--------------------------------------------------------------
		
		
		List<CustomerOutput1> list_1=new ArrayList<CustomerOutput1>();
		CustomerOutput1 output_1=new CustomerOutput1();
		//output.setId(input.getId());
		output_1.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
		output_1.setGender(input.getGender());
		
		CustomerOutput1 output_2=new CustomerOutput1();
		//output.setId(input.getId());
		output_2.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
		output_2.setGender(input.getGender());
		
		list_1.add(output_1);
		list_1.add(output_2);
		//--------------------------------------------------------------
		
		List<CustomerOutput> updatelist=new ArrayList<CustomerOutput>();
		CustomerOutput outputUpdate=new CustomerOutput();
		outputUpdate.setId(input.getId());
		outputUpdate.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
		outputUpdate.setGender(input.getGender());
		
		CustomerOutput outputUpdate1=new CustomerOutput();
		outputUpdate1.setId(input.getId());
		outputUpdate1.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
		outputUpdate1.setGender(input.getGender());
		
		updatelist.add(outputUpdate);
		updatelist.add(outputUpdate1);
		
		//--------------------------------------------------------------
		
		
				List<CustomerOutput1> updatelist_1=new ArrayList<CustomerOutput1>();
				CustomerOutput1 outputUpdate_1=new CustomerOutput1();
				outputUpdate_1.setId(input.getId());
				outputUpdate_1.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
				outputUpdate_1.setGender(input.getGender());
				
				CustomerOutput1 outputUpdate_2=new CustomerOutput1();
				outputUpdate_2.setId(input.getId());
				outputUpdate_2.setName(input.getName()+"|"+input.getId()+"|"+r.nextInt(1000));
				outputUpdate_2.setGender(input.getGender());
				
				updatelist_1.add(outputUpdate_1);
				updatelist_1.add(outputUpdate_2);

				//--------------------------------------------------------------

		CustomerOutputWriter cust=new CustomerOutputWriter();
		cust.setList(list);
		cust.setList1(list_1);
		cust.setUpdateList(updatelist);
		cust.setUpdateList1(updatelist_1);
		
		
		
		return cust;
	}
	

}
