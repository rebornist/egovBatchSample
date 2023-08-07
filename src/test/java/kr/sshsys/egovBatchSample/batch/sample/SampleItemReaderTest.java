package kr.sshsys.egovBatchSample.batch.sample;

import kr.sshsys.egovBatchSample.batch.sample.entity.Sample;
import kr.sshsys.egovBatchSample.batch.sample.util.SamplePostItemReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/springmvc/dispatcher-servlet.xml",
        "file:src/main/resources/context/*.xml"
})
public class SampleItemReaderTest {

    @Autowired
    private SamplePostItemReader<Sample> reader;


    @Test
    public void samplePostItemReaderTest() {
        Sample sp = reader.read();
        System.out.println(sp.toString());
    }
}
