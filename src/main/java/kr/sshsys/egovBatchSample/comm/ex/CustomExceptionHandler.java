package kr.sshsys.egovBatchSample.comm.ex;

import kr.sshsys.egovBatchSample.comm.errorLog.ErrorLogMapper;
import kr.sshsys.egovBatchSample.comm.errorLog.ErrorLogVO;
import kr.sshsys.egovBatchSample.comm.util.EgovMessageSource;
import kr.sshsys.egovBatchSample.comm.web.response.RestResponseVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<RestResponseVO> defaultException(Exception e) {

        logger.error("Default Exception: {}", e.getMessage());

        return new ResponseEntity<>(
                                new RestResponseVO<>(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        null),
                                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RestResponseVO> validationException(ValidationException e) {

        logger.error("Validation Exception: {}", e.getMessage());

        return new ResponseEntity<>(
                                new RestResponseVO<>(
                                        HttpStatus.BAD_REQUEST.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        null),
                                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommRestException.class)
    public ResponseEntity<?> commRestException(CommRestException e) {

        logger.error("Rest Exception: {}", e.getMessage());

        return new ResponseEntity<>(new RestResponseVO<>(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                    null),
                                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional(rollbackFor = Exception.class)
    @ExceptionHandler(CommBatchException.class)
    public void batchException(CommBatchException e) throws Exception {

        logger.error("Batch Exception: {}", e.getMessage());

        sqlSessionTemplate.getMapper(ErrorLogMapper.class)
                            .save(new ErrorLogVO().builder()
                                                    .jobName(e.getJobName())
                                                    .stepName(e.getStepName())
                                                    .message(e.getMessage())
                                                    .build());

    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {

        logger.error("Bind Exception: {}", e.getMessage());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(new RestResponseVO<>(
                                        HttpStatus.BAD_REQUEST.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        errors),
                                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodException(MethodArgumentNotValidException e) {

        logger.error("Method Not Allowed Exception: {}", e.getMessage());

        return new ResponseEntity<>(new RestResponseVO<>(
                                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                                        messageSource.getMessage("fail.common.msg"),
                                        null),
                            HttpStatus.METHOD_NOT_ALLOWED);
    }

}
