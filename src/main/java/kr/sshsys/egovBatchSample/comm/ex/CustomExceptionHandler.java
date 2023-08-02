package kr.sshsys.egovBatchSample.comm.ex;

import kr.sshsys.egovBatchSample.comm.log.dto.ErrorLogDto;
import kr.sshsys.egovBatchSample.comm.log.mapper.ErrorLogMapper;
import kr.sshsys.egovBatchSample.comm.util.EgovMessageSource;
import kr.sshsys.egovBatchSample.comm.web.rest.response.RestResponseDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    private EgovMessageSource messageSource;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponseDto> defaultException(Exception e) {

        logger.error("Default Exception: {}", e.getMessage());

        return new ResponseEntity<>(
                                new RestResponseDto<>(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        messageSource.getMessage("fail.common.msg")),
                                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RestResponseDto> validationException(ValidationException e) {

        logger.error("Validation Exception: {}", e.getMessage());

        return new ResponseEntity<>(
                                new RestResponseDto<>(
                                        HttpStatus.BAD_REQUEST.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        messageSource.getMessage("fail.common.msg")),
                                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommRestException.class)
    public ResponseEntity<?> restException(CommRestException e) {

        logger.error("Rest Exception: {}", e.getMessage());

        return new ResponseEntity<>(new RestResponseDto<>(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        messageSource.getMessage("fail.common.msg")),
                                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommBatchException.class)
    public ResponseEntity<?> batchException(CommBatchException e) {

        logger.error("Batch Exception: {}", e.getMessage());

        try {
            sqlSessionTemplate.getMapper(ErrorLogMapper.class)
                    .save(new ErrorLogDto().builder()
                                            .jobName(e.getJobName())
                                            .stepName(e.getStepName())
                                            .message(e.getMessage())
                                            .build());
        } catch (Exception ne) {
            throw new CommRestException(ne.getMessage());
        }

        return new ResponseEntity<>(new RestResponseDto<>(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        messageSource.getMessage("fail.common.msg")),
                                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {

        logger.error("Bind Exception: {}", e.getMessage());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(new RestResponseDto<>(
                                        HttpStatus.BAD_REQUEST.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        errors),
                                HttpStatus.BAD_REQUEST);
    }

}
