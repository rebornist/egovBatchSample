package kr.sshsys.egovBatchSample.batch.sample;

import kr.sshsys.egovBatchSample.config.DefaultBatchConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/springmvc/dispatcher-servlet.xml",
        "file:src/main/resources/context/*.xml"
})
public class SampleJobTest {

    @Autowired
    private DefaultBatchConfig defaultBatchConfig;

    @Autowired
    private Job samplePostJob;

    @Test
    public void samplePostJobTest() throws Exception {
        JobExecution jobExecution = defaultBatchConfig.runJob(samplePostJob);
        samplePostJob.execute(jobExecution);
        jobExecution.setExitStatus(new ExitStatus(jobExecution.getExitStatus().getExitCode(), "Test Sample Job"));
    }


}
