package kr.sshsys.egovBatchSample.batch.sample.util;

import kr.sshsys.egovBatchSample.batch.sample.entity.Sample;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@StepScope
public class SamplePostItemWriter<T> implements ItemWriter<Sample> {

    private static final String OUTPUT_FILENAME = "C:/eGov/workspace/egovBatchSample2/data/output_" +
                                                    LocalDateTime.now().toString().replaceAll("[T\\-:.]", "");

    @Override
    public void write(List<? extends Sample> items) throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILENAME+".txt", true))) {
            for (Sample item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            throw new Exception("Issue in writing data to the file: " + OUTPUT_FILENAME, e);
        }
    }
}