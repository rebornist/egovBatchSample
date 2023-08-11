package kr.sshsys.egovBatchSample.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 *
 * SchedulingConfig
 * Scheduling 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Job samplePostJob;


    /**
     * runSamplePostJob
     * Sample Post Job 실행 메소드
     * 1시간 마다 실행
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void runSamplePostJob() throws Exception {
        System.out.println("runSamplePostJob => " + LocalDateTime.now());

        JobExecution jobExecution = jobRepository.createJobExecution(samplePostJob.getName(),
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters()
        );
        samplePostJob.execute(jobExecution);
    }

}
