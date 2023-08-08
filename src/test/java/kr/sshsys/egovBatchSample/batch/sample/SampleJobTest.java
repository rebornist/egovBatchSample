package kr.sshsys.egovBatchSample.batch.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
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
    private Job samplePostJob;

    @Test
    public void samplePostJobTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addLong("jobId", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobRepository.createJobExecution(samplePostJob.getName(), jobParameters);
        samplePostJob.execute(jobExecution);
        jobExecution.setExitStatus(new ExitStatus(jobExecution.getExitStatus().getExitCode(), "Test Sample Job"));
    }


}
