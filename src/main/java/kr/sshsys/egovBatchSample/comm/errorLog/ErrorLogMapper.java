package kr.sshsys.egovBatchSample.comm.errorLog;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ErrorLogMapper {
    void save(ErrorLogVO vo) throws Exception;
}
