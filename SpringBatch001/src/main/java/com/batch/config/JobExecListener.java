package com.batch.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobExecListener implements JobExecutionListener {

	private long startTime=0,endTime=0;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		startTime=System.currentTimeMillis();
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		endTime=System.currentTimeMillis();
		 System.out.println("JobExecution - afterJob");
	        System.out.println("------------------------------------------------------------------------------------");
	        System.out.println("jobExecution.getCreateTime()="+jobExecution.getCreateTime());
	        System.out.println("jobExecution.getStartTime()="+jobExecution.getStartTime());
	        System.out.println("jobExecution.getEndTime()="+jobExecution.getEndTime());
	        System.out.println("Time requried to process="+(endTime-startTime));
	}

}
