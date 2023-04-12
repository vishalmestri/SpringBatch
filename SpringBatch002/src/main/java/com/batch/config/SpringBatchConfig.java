package com.batch.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.batch.entity.CustomerInput;
import com.batch.entity.CustomerOutput;
import com.batch.entity.CustomerOutput1;
import com.batch.pojo.CustomerOutputWriter;
import com.batch.repo.CustomerInputRepo;
import com.batch.repo.CustomerOutputRepo;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.Order;

@Configuration
@EnableBatchProcessing
//@ComponentScan(basePackages = {"config","controller","repo","entity"})
public class SpringBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private CustomerInputRepo customerInputRepo;
	
	@Autowired
	private CustomerOutputRepo customerOutputRepo;
	
	@Autowired
	private DataSource dataSource;
	
	
	
	 @Bean
	    public TaskExecutor taskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        
	        executor.setCorePoolSize(32);
	        executor.setMaxPoolSize(32);
	        executor.setQueueCapacity(32);
	        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	        executor.setThreadNamePrefix("MultiThreaded-");
	      
	        return executor;
	    }
		
	
	@Bean
	@StepScope
	public ItemReader<CustomerInput> reader(){
		System.out.println("reader-1|Thread="+Thread.currentThread().getName());
		//System.out.println("(dataSource==null)="+(dataSource==null));
		return new JdbcPagingItemReaderBuilder<CustomerInput>()
				.name("Reading")
				
				.dataSource(dataSource)
				
				.selectClause("select * ")
				.fromClause("from customer_input")
				
				.sortKeys(Collections.singletonMap("ID", Order.ASCENDING))
				.beanRowMapper(CustomerInput.class)
				.build();
	}
	
	@Bean
	public AsyncItemProcessor<CustomerInput, CustomerOutputWriter> asyncProcessor() {
        AsyncItemProcessor<CustomerInput, CustomerOutputWriter> asyncItemProcessor = new AsyncItemProcessor<>();
        asyncItemProcessor.setDelegate(processor());
        asyncItemProcessor.setTaskExecutor(taskExecutor());

        return asyncItemProcessor;
    }
	
/*	@Bean
	public CustomerOutput process(CustomerInput input) {
		CustomerOutput output=new CustomerOutput();
		output.setId(input.getId());
		output.setName(input.getName());
		output.setGender(input.getGender());
		return output;
		
	}
	*/
	@Bean
//	@StepScope
	public CustomerProcessoer processor() {
		System.out.println("reader-2");
		return new CustomerProcessoer();
	}
	@Bean
	//@StepScope
	public void writer(CustomerOutput output) {
		System.out.println("writer-1|Thread="+Thread.currentThread().getName());
		customerOutputRepo.save(output);
	}
	
	@Bean
	public JdbcBatchItemWriter<CustomerOutput> customerWriter1(){
		JdbcBatchItemWriter<CustomerOutput> itemWriter= new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO CUSTOMER_OUTPUT (gender,name) VALUES(:gender,:name)");
		
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	}
	
	@Bean
	public JdbcBatchItemWriter<CustomerOutput1> customerWriter2(){
		JdbcBatchItemWriter<CustomerOutput1> itemWriter= new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO CUSTOMER_OUTPUT_1 (gender,name) VALUES(:gender,:name)");
		
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	}
	
	/*
	@Bean
	public JdbcBatchItemWriter<List<CustomerOutput>> customerWriter1(){
		JdbcBatchItemWriter<List<CustomerOutput>> itemWriter= new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO CUSTOMER_OUTPUT VALUES(:gender,:name)");
		
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	}
	
	@Bean
	public JdbcBatchItemWriter<List<CustomerOutput1>> customerWriter2(){
		JdbcBatchItemWriter<List<CustomerOutput1>> itemWriter= new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO CUSTOMER_OUTPUT_1 VALUES(:gender,:name)");
		
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	}
 	*/
	@Bean
	public RepositoryItemWriter<CustomerOutput> writer(){
		System.out.println("writer-2|Thread="+Thread.currentThread().getName());
		RepositoryItemWriter<CustomerOutput> itemWriter=new RepositoryItemWriter<>();
		itemWriter.setRepository(customerOutputRepo);
		itemWriter.setMethodName("save");
		return itemWriter;
	}
	
	@Bean
	public ItemWriter<CustomerOutputWriter> writer1(){
		return new MyItemWriter();
	}
	
	
	 @Bean
	    public AsyncItemWriter<CustomerOutputWriter> asyncWriter() {
	        AsyncItemWriter<CustomerOutputWriter> asyncItemWriter = new AsyncItemWriter<>();
	        asyncItemWriter.setDelegate(writer2());
	        return asyncItemWriter;
	    }
	 
	 @Bean
		public ItemWriter<CustomerOutputWriter> writer2(){
			return new MyNewItemWriter();
		}
	
	@Bean
	public Step step01() {
		System.out.println("STEP...|Thread="+Thread.currentThread().getName());
		return stepBuilderFactory.get("db-setup")//.<CustomerInput,CustomerOutput>chunk(10)
				.<CustomerInput, Future<CustomerOutputWriter>>chunk(100)
				.reader(reader())
				.processor(asyncProcessor())
				.writer(asyncWriter())
				.listener(new StepExecuListner())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public Job job() {
		System.out.println("job...|Thread="+Thread.currentThread().getName());
		return jobBuilderFactory.get("db transer").listener(new JobExecListener()).flow(step01()).end().build();
	}
}
