package com.azoker.admin.service.impl;

import com.azoker.admin.mapper.DoctorMapper;
import com.azoker.pojo.dto.DoctorQueryDto;
import com.azoker.pojo.entity.Doctor;
import com.azoker.pojo.vo.DoctorListVo;
import com.azoker.pojo.vo.DoctorVo;
import com.azoker.service.DoctorService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxd on 2023/7/7
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public DoctorVo findDoctorById(Long doctorId) {
        return doctorMapper.findDoctorById(doctorId);
    }

    @Override
    public List<DoctorListVo> findDoctorByQueryDto(DoctorQueryDto doctorQueryDto) {
        return doctorMapper.findDoctorByQueryDto(doctorQueryDto);
    }
}
