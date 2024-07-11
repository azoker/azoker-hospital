package com.azoker.admin.service.impl;

import com.azoker.admin.mapper.DoctorWorkPlanScheduleMapper;
import com.azoker.pojo.entity.DoctorWorkPlanSchedule;
import com.azoker.service.DoctorWorkPlanScheduleService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxd on 2023/3/31
 */
@Service
public class DoctorWorkPlanScheduleServiceImpl extends ServiceImpl<DoctorWorkPlanScheduleMapper, DoctorWorkPlanSchedule> implements DoctorWorkPlanScheduleService {
    @Override
    public void insert(List<DoctorWorkPlanSchedule> doctorWorkPlanScheduleList) {

        //1.保存出诊计划
        this.saveBatch(doctorWorkPlanScheduleList);

        //2.redis缓存出诊计划

    }
}
