package com.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.batch.entity.CustomerInput;
import com.batch.entity.CustomerOutput;
import com.batch.entity.CustomerOutput1;
import com.batch.pojo.CustomerOutputWriter;
import com.batch.repo.CustomerOutputRepo;
import com.batch.repo.CustomerOutputRepo1;

public class CustomerProcessoerNew implements ItemProcessor<CustomerInput, CustomerOutputWriter> {

	
	/*@Autowired
	private CustomerOutputRepo customerOutputRepo;

	@Autowired
	private CustomerOutputRepo1 customerOutputRepo1;
	*/
	//@Autowired
	//JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("readerCustomerOutput")
	JdbcPagingItemReader<CustomerOutput> itemReader;
	
	@Autowired
	@Qualifier("readerCustomerOutput1")
	JdbcPagingItemReader<CustomerOutput1> itemReader1;
	
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
		
		/*
		Collection<CustomerOutput> outputlist_1=customerOutputRepo.findAllActiveUsersNative(input.getName());
		outputlist_1.stream().forEach(c->System.out.println("[1]input.getName()=>"+c));
		Collection<CustomerOutput1> outputlist_2=customerOutputRepo1.findAllActiveUsersNative(input.getName());
		outputlist_2.stream().forEach(c->System.out.println("[2]input.getName()=>"+c));
		*/
	
		/*
		System.out.println("CustomerProcessoer-1-|Thread="+Thread.currentThread().getName());
		List<CustomerOutput> outputlist_1=jdbcTemplate.query("SELECT * FROM Customer_output u WHERE u.name = ?",new Object[] {input.getName()}, 
				new RowMapper<CustomerOutput>() {

					@Override
					public CustomerOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
						CustomerOutput output=new CustomerOutput();
						output.setId(rs.getInt("id"));
						output.setName(rs.getString("name"));
						output.setGender(rs.getString("gender"));
						return output;
					}
		});
		outputlist_1.stream().forEach(c->System.out.println("[1]input.getName()=>"+c));
		System.out.println("CustomerProcessoer-2-|Thread="+Thread.currentThread().getName());
		List<CustomerOutput1> outputlist_2=jdbcTemplate.query("SELECT * FROM Customer_output_1 u WHERE u.name = ?",new Object[] {input.getName()}, 
				new RowMapper<CustomerOutput1>() {

					@Override
					public CustomerOutput1 mapRow(ResultSet rs, int rowNum) throws SQLException {
						CustomerOutput1 output=new CustomerOutput1();
						output.setId(rs.getInt("id"));
						output.setName(rs.getString("name"));
						output.setGender(rs.getString("gender"));
						return output;
					}
		});
		outputlist_2.stream().forEach(c->System.out.println("[2]input.getName()=>"+c));
		System.out.println("CustomerProcessoer-3-|Thread="+Thread.currentThread().getName());		
		*/
		
		
		Map<String, Object> parameterValues=new HashMap<>();
		parameterValues.put("name", input.getId());
		itemReader.setParameterValues(parameterValues);
		//ExecutionContext executionContext = new ExecutionContext();
		//itemReader.open(executionContext);
		CustomerOutput customerout=new CustomerOutput();
		List<CustomerOutput> outputlist_1=new ArrayList<CustomerOutput>();
		boolean flag=true;
		
		do {
			//System.out.println("Before calling read|Thread="+Thread.currentThread().getName());
			if(!flag) {
				outputlist_1.add(customerout);
			}else {
				flag=false;
			}
			customerout=itemReader.read();
			//System.out.println("After calling read|Thread="+Thread.currentThread().getName());
			
		}
		while(customerout!=null);
	//	itemReader.close();
		System.out.println("after closing item reader | outputlist_1"+outputlist_1.size()+"|Thread="+Thread.currentThread().getName());
		//outputlist_1.stream().forEach(c->System.out.println("[1]input.getName()=>"+c));
		//-------------------------------------------
		
		itemReader1.setParameterValues(parameterValues);
		//ExecutionContext executionContext = new ExecutionContext();
		//itemReader.open(executionContext);
		CustomerOutput1 customerout1=new CustomerOutput1();
		List<CustomerOutput1> outputlist_2=new ArrayList<CustomerOutput1>();
		flag=true;
		
		do {
			//System.out.println("Before calling read|Thread="+Thread.currentThread().getName());
			if(!flag) {
				outputlist_2.add(customerout1);
			}else {
				flag=false;
			}
			customerout1=itemReader1.read();
			//System.out.println("After calling read|Thread="+Thread.currentThread().getName());
			
		}
		while(customerout1!=null);
			
		
	//	itemReader.close();
		System.out.println("after closing item reader | outputlist_2"+outputlist_2.size()+"|Thread="+Thread.currentThread().getName());
		//outputlist_2.stream().forEach(c->System.out.println("[1]input.getName()=>"+c));
		
		
		//-------------------------------------------
		return cust;
	}
	

}
