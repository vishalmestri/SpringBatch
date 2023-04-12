package com.batch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.entity.CustomerOutput;
import com.batch.pojo.CustomerOutputWriter;
import com.batch.repo.CustomerOutputRepo;
import com.batch.repo.CustomerOutputRepo1;

public class MyItemWriter implements ItemWriter<CustomerOutputWriter>{

	@Autowired
	private CustomerOutputRepo customerOutputRepo;

	@Autowired
	private CustomerOutputRepo1 customerOutputRepo1;
	
	@Override
	public void write(List<? extends CustomerOutputWriter> items) throws Exception {
		// TODO Auto-generated method stub
		items.stream().forEach(item-> {
			customerOutputRepo.saveAllAndFlush(item.getList());
			customerOutputRepo1.saveAllAndFlush(item.getList1());
		});
		//items.stream().forEach(item-> customerOutputRepo.saveAllAndFlush(item.getList()));
	}
	
	

}
