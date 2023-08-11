package kr.sshsys.egovBatchSample.batch.sample;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/springmvc/dispatcher-servlet.xml",
        "file:src/main/resources/context/*.xml"
})
public class SampleJobTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private Job sampleJob;

    @Test
    public void sampleJobTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addLong("jobId", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobRepository.createJobExecution(sampleJob.getName(), jobParameters);
//        sampleJob.execute(jobExecution);
        jobExecution.setExitStatus(new ExitStatus(jobExecution.getExitStatus().getExitCode(), "Test Sample Job"));

        jobOperator.start(sampleJob.getName(), jobParameters.toString());

//        egovBatchRunner.start(sampleJob.getName(), egovBatchRunner.createUniqueJobParameters());
    }


}
