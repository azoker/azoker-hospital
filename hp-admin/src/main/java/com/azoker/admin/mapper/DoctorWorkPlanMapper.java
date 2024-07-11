package com.azoker.admin.mapper;

import com.azoker.pojo.dto.DoctorScheduleQueryDto;
import com.azoker.pojo.dto.WorkPlanInQueryDto;
import com.azoker.pojo.dto.WorkPlanScheduleQueryDto;
import com.azoker.pojo.entity.DoctorWorkPlan;
import com.azoker.pojo.vo.DoctorScheduleVo;
import com.azoker.pojo.vo.DoctorWorkPlanVo;
import com.azoker.pojo.vo.WorkPlanDateVo;
import com.azoker.pojo.vo.WorkPlanScheduleVo;
import com.mybatisflex.core.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zxd on 2023/3/31
 */
public interface DoctorWorkPlanMapper extends BaseMapper<DoctorWorkPlan> {

    /**
     * 用户端根据诊室id和日期查询科室有医生出诊的日期
     * @param workPlanInQueryDto
     * @return
     */
    List<String> searchCanRegisterInDateRange(WorkPlanInQueryDto workPlanInQueryDto);


    /**
     * 用户端查询某诊室某天医生出诊信息
     * @param workPlanScheduleQueryDto
     * @return
     */
    List<WorkPlanDateVo> searchDeptSubDoctorPlanInDay(WorkPlanScheduleQueryDto workPlanScheduleQueryDto);

    /**
     * 根据条件查询门诊出诊计划
     * @param workPlanInQueryDto
     * @return
     */
    List<DoctorWorkPlanVo> findDoctorWorkPlanVoByQueryDto(WorkPlanInQueryDto workPlanInQueryDto);


    /**
     * 根据条件查询医生出诊时段详情
     * @param workPlanScheduleQueryDto
     * @return
     */
    List<WorkPlanScheduleVo> findDoctorWorkPlanScheduleByQueryDto(WorkPlanScheduleQueryDto workPlanScheduleQueryDto);


    /**
     * 根据日期和医生id查询医生出诊时段信息
     * @param doctorScheduleQueryDto
     * @return
     */
    List<DoctorScheduleVo> searchDoctorWorkPlanSchedule(DoctorScheduleQueryDto doctorScheduleQueryDto);

}
