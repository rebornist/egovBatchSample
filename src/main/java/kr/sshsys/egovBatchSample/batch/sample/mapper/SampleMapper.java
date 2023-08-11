package kr.sshsys.egovBatchSample.batch.sample.mapper;

import kr.sshsys.egovBatchSample.batch.sample.entity.SampleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * SampleMapper
 * SampleMapper 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Mapper
public interface SampleMapper {
    List<SampleVO> searchAll();
}
