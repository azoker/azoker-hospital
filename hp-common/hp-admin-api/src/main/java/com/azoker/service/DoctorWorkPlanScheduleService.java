package com.azoker.service;

import com.azoker.pojo.entity.DoctorWorkPlanSchedule;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * Created by zxd on 2023/3/31
 */
public interface DoctorWorkPlanScheduleService extends IService<DoctorWorkPlanSchedule> {

    /**
     *添加出诊时间段
     * @param doctorWorkPlanScheduleList
     */
    void insert(List<DoctorWorkPlanSchedule> doctorWorkPlanScheduleList);

}

