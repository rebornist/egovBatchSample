package kr.sshsys.egovBatchSample.comm.log.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.sshsys.egovBatchSample.comm.log.dto.ErrorLogDto;

@Mapper
public interface ErrorLogMapper {
    void save(ErrorLogDto dto) throws Exception;
}
