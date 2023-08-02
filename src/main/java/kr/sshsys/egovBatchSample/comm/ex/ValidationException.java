package kr.sshsys.egovBatchSample.comm.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    private Map<String, String> errorMap;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception e) {
        super(e);
    }

    public ValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public ValidationException(Exception e, Map<String, String> errorMap) {
        super(e);
        this.errorMap = errorMap;
    }
}
