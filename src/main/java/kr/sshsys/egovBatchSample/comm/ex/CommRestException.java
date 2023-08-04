package kr.sshsys.egovBatchSample.comm.ex;

public class CommRestException extends RuntimeException {
    public CommRestException() {
    }

    public CommRestException(String message) {
        super(message);
    }

    public CommRestException(Exception e) {
        super(e);
    }

}
