package kr.sshsys.egovBatchSample.comm.ex;

import lombok.Getter;


@Getter
public class CommBatchException extends RuntimeException {

    private String jobName;
    private String stepName;

    public CommBatchException() {
    }

    public CommBatchException(Exception e) {
        super(e);
    }

    public CommBatchException(Exception e, String jobName) {
        super(e);
        this.jobName = jobName;
    }

    public CommBatchException(Exception e, String jobName, String stepName) {
        super(e);
        this.jobName = jobName;
        this.stepName = stepName;
    }

    public CommBatchException(String msg) {
        super(msg);
    }

    public CommBatchException(String msg, String jobName) {
        super(msg);
        this.jobName = jobName;
    }

    public CommBatchException(String msg, String jobName, String stepName) {
        super(msg);
        this.jobName = jobName;
        this.stepName = stepName;
    }

}
