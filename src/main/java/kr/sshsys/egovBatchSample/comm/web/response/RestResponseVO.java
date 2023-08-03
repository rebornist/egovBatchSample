package kr.sshsys.egovBatchSample.comm.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class RestResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 4906807840940185769L;

    private final int status;
    private final String message;
    private final T data;
}
