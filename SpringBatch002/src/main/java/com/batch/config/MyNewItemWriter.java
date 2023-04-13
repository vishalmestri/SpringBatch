package com.batch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.batch.entity.CustomerOutput;
import com.batch.entity.CustomerOutput1;
import com.batch.pojo.CustomerOutputWriter;

public class MyNewItemWriter implements ItemWriter<CustomerOutputWriter>{

	@Autowired
	@Qualifier(value = "customerInsert")
	 private JdbcBatchItemWriter<CustomerOutput> jdbcBatchItemWriter1;
	
	@Autowired
	@Qualifier(value = "customer1Insert")
	 private JdbcBatchItemWriter<CustomerOutput1> jdbcBatchItemWriter2;

	@Autowired
	@Qualifier(value = "customerUpdate")
	 private JdbcBatchItemWriter<CustomerOutput> jdbcBatchItemWriter3;
	
	@Autowired
	@Qualifier(value = "customer1Update")
	 private JdbcBatchItemWriter<CustomerOutput1> jdbcBatchItemWriter4;

	
	
	@Override
	public void write(List<? extends CustomerOutputWriter> items) throws Exception {
		// TODO Auto-generated method stub
		
		items.stream().forEach(item-> {
			try {
				jdbcBatchItemWriter1.write(item.getList());
				jdbcBatchItemWriter2.write(item.getList1());
				jdbcBatchItemWriter3.write(item.getUpdateList());
				jdbcBatchItemWriter4.write(item.getUpdateList1());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}
			
		});
		
	}

}
