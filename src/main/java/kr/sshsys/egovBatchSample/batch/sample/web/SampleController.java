package kr.sshsys.egovBatchSample.batch.sample.web;

import kr.sshsys.egovBatchSample.comm.ex.CommBatchException;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private Job samplePostJob;

    @GetMapping("/sample.do")
    public String sample() throws CommBatchException {

        throw new CommBatchException("first exception", "testJob", "testStep");

    }
}
