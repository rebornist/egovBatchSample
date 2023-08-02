package kr.sshsys.egovBatchSample.comm.web.rest.response;

import kr.sshsys.egovBatchSample.comm.ex.CommBatchException;
import kr.sshsys.egovBatchSample.comm.util.ObjectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestResponse {

    private final Logger logger = LoggerFactory.getLogger(RestResponse.class);

    private final ObjectHandler bodyBuilder = new ObjectHandler();

    public void successByXml(HttpServletResponse response, Object data) {

        try {
            RestResponseDto dto = new RestResponseDto<Object>(
                    HttpServletResponse.SC_OK,
                    "OK",
                    data
            );

            String responseBody = bodyBuilder.toXml(dto);

            response.setContentType("application/xml");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(responseBody);

            logger.info("responseBody: {}", responseBody);
        } catch (IOException e) {
            throw new CommBatchException(e);
        }

    }

    public void successByJson(HttpServletResponse response, Object data) {

        try {

            RestResponseDto dto = new RestResponseDto<Object>(
                HttpServletResponse.SC_OK,
                "OK",
                data
            );

            String responseBody = bodyBuilder.toJson(dto);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(responseBody);

            logger.info("responseBody: {}", responseBody);
        } catch (IOException e) {
            throw new CommBatchException(e);
        }

    }

    public void failByXml(HttpServletResponse response, String message, HttpStatus httpStatus) {

        try {

            RestResponseDto dto = new RestResponseDto<Object>(
                    httpStatus.value(),
                    message,
                    null
            );

            String responseBody = bodyBuilder.toXml(dto);

            response.setContentType("application/xml");
            response.setCharacterEncoding("utf-8");
            response.setStatus(httpStatus.value());
            response.getWriter().write(responseBody);

            logger.error("msg: {}, responseBody: {}", message, responseBody);

        } catch (IOException e) {
            throw new CommBatchException(e);
        }

    }

    public void failByJson(HttpServletResponse response, String message, HttpStatus httpStatus) {

        try {

            RestResponseDto dto = new RestResponseDto<Object>(
                    httpStatus.value(),
                    message,
                    null
            );

            String responseBody = bodyBuilder.toJson(dto);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(httpStatus.value());
            response.getWriter().write(responseBody);

            logger.error("msg: {}, responseBody: {}", message, responseBody);
        } catch (IOException e) {
            throw new CommBatchException(e);
        }

    }

}
