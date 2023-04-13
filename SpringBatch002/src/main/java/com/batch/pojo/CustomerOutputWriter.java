package com.batch.pojo;

import java.util.List;

import com.batch.entity.CustomerOutput;
import com.batch.entity.CustomerOutput1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerOutputWriter {

	List<CustomerOutput> list;
	List<CustomerOutput1> list1;
	List<CustomerOutput> updateList;
	List<CustomerOutput1> updateList1;
}
