package kr.sshsys.egovBatchSample.sample.web;

import kr.sshsys.egovBatchSample.comm.ex.CommBatchException;
import kr.sshsys.egovBatchSample.config.DefaultBatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private DefaultBatchConfig defaultBatchConfig;

    @Autowired
    private Job samplePostJob;

    @GetMapping("/sample.do")
    public String sample() throws Exception {

        throw new CommBatchException("first exception", "testJob", "testStep");

    }
}