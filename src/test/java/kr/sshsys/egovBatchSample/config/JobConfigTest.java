package kr.sshsys.egovBatchSample.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/springmvc/dispatcher-servlet.xml",
        "file:src/main/resources/context/*.xml"
})
public class JobConfigTest {


    @Autowired
    private DefaultBatchConfig defaultBatchConfig;

    @Autowired
    private Job samplePostJob;



    @Test
    public void jobConfigTest() throws Exception {
        JobExecution je = defaultBatchConfig.runJob(samplePostJob);

        Assert.assertSame(je.getStatus().name().equals("STARTING"), true);
    }
}
