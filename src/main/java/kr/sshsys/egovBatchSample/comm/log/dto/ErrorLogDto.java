package kr.sshsys.egovBatchSample.comm.log.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorLogDto {

    private String jobName;
    private String stepName;
    private String message;

}
