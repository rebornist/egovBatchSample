package kr.sshsys.egovBatchSample.comm.web.rest.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class RestResponseDto<T> implements Serializable {
    private final int status;
    private final String message;
    private final T data;
}
