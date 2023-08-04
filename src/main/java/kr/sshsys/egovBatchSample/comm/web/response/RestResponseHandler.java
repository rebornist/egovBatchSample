package kr.sshsys.egovBatchSample.comm.web.response;

import kr.sshsys.egovBatchSample.comm.ex.CommBatchException;
import kr.sshsys.egovBatchSample.comm.util.ObjectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(RestResponseHandler.class);

    private final ObjectHandler bodyBuilder = new ObjectHandler();

    public void successByXml(HttpServletResponse response, Object data) {

        try {
            RestResponseVO<Object> vo = new RestResponseVO<>(
                    HttpServletResponse.SC_OK,
                    "OK",
                    data
            );

            String responseBody = bodyBuilder.toXml(vo);

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

            RestResponseVO<Object> vo = new RestResponseVO<>(
                    HttpServletResponse.SC_OK,
                    "OK",
                    data
            );

            String responseBody = bodyBuilder.toJson(vo);

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

            RestResponseVO<Object> vo = new RestResponseVO<>(
                    httpStatus.value(),
                    message,
                    null
            );

            String responseBody = bodyBuilder.toXml(vo);

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

            RestResponseVO<Object> vo = new RestResponseVO<>(
                    httpStatus.value(),
                    message,
                    null
            );

            String responseBody = bodyBuilder.toJson(vo);

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
