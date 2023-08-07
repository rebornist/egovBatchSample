package kr.sshsys.egovBatchSample.batch.sample.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class SampleJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().put("outputFileName", "C:/eGov/workspace/egovBatchSample2/data/output_" +
                jobExecution.getCreateTime().toString().replaceAll("[T\\-:.]", "") + ".txt");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
