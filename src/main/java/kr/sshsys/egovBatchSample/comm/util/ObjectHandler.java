package kr.sshsys.egovBatchSample.comm.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.sshsys.egovBatchSample.comm.ex.CommBatchException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectHandler {

    public ObjectHandler() {
    }

    public String toXml(Object obj) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            marshaller.marshal(obj, sw);

            return sw.toString();
        } catch (JAXBException e) {
            throw new CommBatchException(e);
        }
    }

    public String toJson(Object obj) {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(obj);
        } catch (Exception e) {
            throw new CommBatchException(e);
        }
    }

    /**
     * Object Key의 필드명을 가져오는 메소드
     * @param object
     * @return String[]
     */
    public static String[] getFieldNames(Object object) {

        /* 필드명을 담을 List 생성 */
        List<String> fieldNames = new ArrayList<>();

        /* Object의 필드명을 가져와서 List에 담기 */
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        /* List를 String[]로 변환하여 리턴 */
        return fieldNames.toArray(new String[0]);
    }

}
