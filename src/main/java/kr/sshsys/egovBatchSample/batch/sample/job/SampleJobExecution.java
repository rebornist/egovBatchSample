package kr.sshsys.egovBatchSample.batch.sample.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SampleJobExecution
 * SampleJobExecution 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
@EnableBatchProcessing
public class SampleJobExecution {

    /** JobBuilderFactory */
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    /**
     * samplePostJob
     * Job 설정을 담당하는 메소드
     * @return Job
     */
    @Bean
    public Job sampleJob(Step samplePostStep) {
        return jobBuilderFactory.get("SampleJob")
                .incrementer(new RunIdIncrementer())
                .start(samplePostStep)
                .build();
    }

}
