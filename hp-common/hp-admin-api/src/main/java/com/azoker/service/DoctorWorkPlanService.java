package com.azoker.service;

import cn.hutool.json.JSONArray;
import com.azoker.pojo.dto.DoctorScheduleQueryDto;
import com.azoker.pojo.dto.DoctorWorkPlanAddDto;
import com.azoker.pojo.dto.WorkPlanInQueryDto;
import com.azoker.pojo.dto.WorkPlanScheduleQueryDto;
import com.azoker.pojo.entity.DoctorWorkPlan;
import com.azoker.pojo.vo.DoctorScheduleVo;
import com.azoker.pojo.vo.WorkPlanDateVo;
import com.azoker.pojo.vo.WorkPlanScheduleVo;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DoctorWorkPlanService extends IService<DoctorWorkPlan> {

    /**
     * 添加医生出诊计划
     * @param doctorWorkPlanAddDto
     * @return
     */
    void insertDoctorWorkPlan(DoctorWorkPlanAddDto doctorWorkPlanAddDto);


    /**
     * 根据条件查询指定门诊出诊信息 LayUI对应后端实现
     * @param workPlanInQueryDto
     * @return
     */
     Collection searchWorkPlanInRange(WorkPlanInQueryDto workPlanInQueryDto);


    /**
     * 根据条件查询指定门诊出诊信息 Vue对应后端实现
     * @param workPlanInQueryDto
     * @param dateList
     * @return
     */
    JSONArray searchWorkPlanInRange(WorkPlanInQueryDto workPlanInQueryDto, ArrayList dateList);

    /**
     * 根据条件查询诊室出诊医生时间段信息
     * @param workPlanScheduleQueryDto
     * @return
     */
    Collection<WorkPlanScheduleVo> searchConSchedule(WorkPlanScheduleQueryDto workPlanScheduleQueryDto);


    /**
     * 用户端根据诊室id和日期查询科室有医生出诊的日期
     * @param workPlanInQueryDto
     * @return
     */
    List<Map> searchCanRegisterInDateRange(WorkPlanInQueryDto workPlanInQueryDto);


    /**
     * 用户端查询某诊室某天医生出诊信息
     * @param workPlanScheduleQueryDto
     * @return
     */
    List<WorkPlanDateVo> searchDeptSubDoctorPlanInDay(WorkPlanScheduleQueryDto workPlanScheduleQueryDto);


    /**
     * 根据日期和医生id查询医生出诊时段信息
     * @param doctorScheduleQueryDto
     * @return
     */
    List<DoctorScheduleVo> searchDoctorWorkPlanSchedule(DoctorScheduleQueryDto doctorScheduleQueryDto);


}
