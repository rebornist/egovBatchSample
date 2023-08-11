package kr.sshsys.egovBatchSample.batch.sample.entity;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Sample Entity
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 */
@Getter
@ToString
public class SampleVO implements Serializable {

    private static final long serialVersionUID = 4256446889040622647L;

    private Long id;

    private String instlPlceNm;

    private String instlPlceDtil;

    private String svcProvdCmpnyNm;

    private String wifiSsid;

    private String instlYm;

    private String lnmAdrs;

    private String rnAdrs;

    private String admdCd;

    private String admdNm;

    private String lgdngCd;

    private String lgdngNm;

    private String mngmtInsttNm;

    private String telno;

    private Double la;

    private Double lo;

    private String dataStdrDe;

}
