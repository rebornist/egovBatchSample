package kr.sshsys.egovBatchSample.batch.sample.step;

import kr.sshsys.egovBatchSample.batch.sample.entity.SampleVO;
import kr.sshsys.egovBatchSample.batch.sample.util.SamplePostItemReader;
import kr.sshsys.egovBatchSample.batch.sample.util.SamplePostItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * SampleFileWriteStepExecution
 * Step 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
public class SampleStepExecution {

    /** StepBuilderFactory */
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * sampleFileWriteStep
     * Step 설정을 담당하는 메소드
     * @return Step
     */
    @Bean
    @JobScope
    public Step samplePostStep(SamplePostItemReader itemReader,
                               SamplePostItemWriter itemWriter) {
        return stepBuilderFactory.get("SampleStep")
                .<SampleVO, SampleVO>chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

}
