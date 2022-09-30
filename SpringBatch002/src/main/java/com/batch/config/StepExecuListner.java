package com.batch.config;



import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;

public class StepExecuListner implements StepExecutionListener{
	 @Override
	    public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
	        System.out.println("StepExecutionListener - beforeStep");
	        System.out.println("StepExecutionListener - beforeStep " );

	    }

	    @Override
	    public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
	        System.out.println("StepExecutionListener - afterStep");
	        System.out.println("------------------------------------------------------------------------------------");
	        System.out.println("StepExecutionListener - afterStep:getCommitCount=" +  stepExecution.getCommitCount());
	        System.out.println("StepExecutionListener - afterStep:getFilterCount=" +  stepExecution.getFilterCount());
	        System.out.println("StepExecutionListener - afterStep:getProcessSkipCount=" +  stepExecution.getProcessSkipCount());
	        System.out.println("StepExecutionListener - afterStep:getReadCount=" +  stepExecution.getReadCount());
	        System.out.println("StepExecutionListener - afterStep:getReadSkipCount=" +  stepExecution.getReadSkipCount());
	        System.out.println("StepExecutionListener - afterStep:getRollbackCount=" +  stepExecution.getRollbackCount());
	        System.out.println("StepExecutionListener - afterStep:getWriteCount=" +  stepExecution.getWriteCount());
	        System.out.println("StepExecutionListener - afterStep:getWriteSkipCount=" +  stepExecution.getWriteSkipCount());
	        System.out.println("StepExecutionListener - afterStep:getStepName=" +  stepExecution.getStepName());
	        System.out.println("StepExecutionListener - afterStep:getSummary=" +  stepExecution.getSummary());
	        System.out.println("StepExecutionListener - afterStep:getStartTime=" +  stepExecution.getStartTime());
	        System.out.println("StepExecutionListener - afterStep:getEndTime=" +  stepExecution.getEndTime());
	        System.out.println("StepExecutionListener - afterStep:getLastUpdated=" +  stepExecution.getLastUpdated());
	        System.out.println("StepExecutionListener - afterStep:getExitStatus=" +  stepExecution.getExitStatus());
	        System.out.println("StepExecutionListener - afterStep:getFailureExceptions=" +  stepExecution.getFailureExceptions());
	        

	        
	        System.out.println("------------------------------------------------------------------------------------");

	        return null;
	    }
}
