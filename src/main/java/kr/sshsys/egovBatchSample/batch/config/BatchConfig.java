package kr.sshsys.egovBatchSample.batch.config;

import egovframework.rte.bat.core.launch.support.EgovBatchRunner;
import kr.sshsys.egovBatchSample.comm.incrementer.CubridDataFieldMaxValueIncrementerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

import javax.sql.DataSource;

@Configuration
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private DataSource dataSource;

    public BatchConfig(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setDatabaseType("oracle");
        factory.setTransactionManager(new DataSourceTransactionManager(dataSource));
        factory.setIncrementerFactory(new CubridDataFieldMaxValueIncrementerFactory(dataSource));
        factory.setLobHandler(new DefaultLobHandler());
        return factory.getObject();
    }

    @Override
    public JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(createJobRepository());
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        return createJobRepository();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        return createJobLauncher();
    }

    @Bean
    public JobExplorer jobExplorer() throws Exception {
        JobExplorerFactoryBean factory = new JobExplorerFactoryBean();
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JobRegistry jobRegistry() {
        return new MapJobRegistry();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) throws Exception {
        JobRegistryBeanPostProcessor postProcessor  = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        postProcessor.afterPropertiesSet();
        return postProcessor ;
    }

    @Bean
    public JobOperator jobOperator(JobExplorer jobExplorer, JobLauncher jobLauncher, JobRegistry jobRegistry, JobRepository jobRepository) {
        SimpleJobOperator jobOperator = new SimpleJobOperator();
        jobOperator.setJobExplorer(jobExplorer);
        jobOperator.setJobLauncher(jobLauncher);
        jobOperator.setJobRegistry(jobRegistry);
        jobOperator.setJobRepository(jobRepository);
        return jobOperator;
    }

    @Bean
    public EgovBatchRunner egovBatchRunner(JobOperator jobOperator, JobExplorer jobExplorer, JobRepository jobRepository) {
        return new EgovBatchRunner(jobOperator, jobExplorer, jobRepository);
    }

}
