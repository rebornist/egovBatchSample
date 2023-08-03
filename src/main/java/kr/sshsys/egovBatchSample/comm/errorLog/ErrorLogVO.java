package kr.sshsys.egovBatchSample.comm.errorLog;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jobName;
    private String stepName;
    private String message;

}
