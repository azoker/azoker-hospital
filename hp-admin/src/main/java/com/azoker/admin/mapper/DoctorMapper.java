package com.azoker.admin.mapper;

import com.azoker.pojo.dto.DoctorQueryDto;
import com.azoker.pojo.entity.Doctor;
import com.azoker.pojo.vo.DoctorListVo;
import com.azoker.pojo.vo.DoctorVo;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * Created by zxd on 2023/7/7
 */
public interface DoctorMapper extends BaseMapper<Doctor> {

    /**
     * 根据id查找医生信息
     * @param doctorId
     * @return
     */
    DoctorVo findDoctorById(Long doctorId);

    /**
     * 根据条件动态查询医生列表
     * @param doctorQueryDto
     * @return
     */
    List<DoctorListVo> findDoctorByQueryDto(DoctorQueryDto doctorQueryDto);

}
