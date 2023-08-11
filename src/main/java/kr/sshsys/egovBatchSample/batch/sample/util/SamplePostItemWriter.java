package kr.sshsys.egovBatchSample.batch.sample.util;

import kr.sshsys.egovBatchSample.batch.sample.entity.SampleVO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@StepScope
public class SamplePostItemWriter implements ItemWriter<SampleVO> {

    private static final String OUTPUT_FILENAME = "C:/data/output_" +
                                                    LocalDateTime.now().toString().replaceAll("[T\\-:.]", "");

    @Override
    public void write(List<? extends SampleVO> items) throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILENAME+".txt", true))) {
            for (SampleVO item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            throw new Exception("Issue in writing data to the file: " + OUTPUT_FILENAME, e);
        }
    }
}