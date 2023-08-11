package kr.sshsys.egovBatchSample.comm.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommBatchException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(CommBatchException.class);

    public CommBatchException() {
        logger.error("Common Batch Exception Unknown Error");
    }

    public CommBatchException(Exception e) {
        super(e);
        logger.error("Common Batch Exception: message={}", e.getMessage());
    }

    public CommBatchException(Exception e, String jobName) {
        super(e);

        logger.error("Common Batch Exception: JobName={}, message={}", jobName, e.getMessage());

    }

    public CommBatchException(Exception e, String jobName, String stepName) {
        super(e);

        logger.error("Common Batch Exception: JobName={}, StepName={}, message={}", jobName, stepName, e.getMessage());

    }

    public CommBatchException(String msg) {
        super(msg);
        logger.error("Common Batch Exception: message={}", msg);
    }

    public CommBatchException(String msg, String jobName) {
        super(msg);

        logger.error("Common Batch Exception: JobName={}, message={}", jobName, msg);

    }

    public CommBatchException(String msg, String jobName, String stepName) {
        super(msg);

        logger.error("Common Batch Exception: JobName={}, StepName={}, message={}", jobName, stepName, msg);
    }

}
