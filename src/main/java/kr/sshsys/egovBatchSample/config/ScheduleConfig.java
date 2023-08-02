package kr.sshsys.egovBatchSample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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

    /** DefaultBatchConfig */
    private final DefaultBatchConfig defaultBatchConfig;

    /** Job */
    private final Job samplePostJob;

    /**
     * 생성자
     * @param defaultBatchConfig
     * @param samplePostJob
     */
    public ScheduleConfig(DefaultBatchConfig defaultBatchConfig, Job samplePostJob) {
        this.defaultBatchConfig = defaultBatchConfig;
        this.samplePostJob = samplePostJob;
    }

    /**
     * runJob1
     * Job 실행 메소드
     */
//    @Scheduled(cron = "0 */2 * * * ?")
//    public void runJob1() throws Exception {
//        defaultBatchConfig.runJob(samplePostJob);
//    }

}
