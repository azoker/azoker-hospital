package com.azoker.admin.controller.app;

import com.azoker.pojo.dto.DoctorScheduleQueryDto;
import com.azoker.pojo.dto.WorkPlanInQueryDto;
import com.azoker.pojo.dto.WorkPlanScheduleQueryDto;
import com.azoker.pojo.entity.ConsultingRoom;
import com.azoker.pojo.entity.Department;
import com.azoker.pojo.vo.DoctorScheduleVo;
import com.azoker.pojo.vo.DoctorVo;
import com.azoker.pojo.vo.WorkPlanDateVo;
import com.azoker.result.Result;
import com.azoker.service.*;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zxd on 2023/4/1
 */
@RestController//TODO 测试app代码，后面不放在该位置，废除
@RequestMapping("/app/dep")
@Slf4j
public class AppDepController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ConsultingRoomService consultingRoomService;

    @Autowired
    private DoctorWorkPlanService doctorWorkPlanService;

    @Autowired
    private DoctorService doctorService;



    @RequestMapping(value = "/searchDeptList",method = RequestMethod.GET)
    public Result searchDeptList(){
        QueryWrapper lambdaQueryWrapper= QueryWrapper.create().from(Department.class);
        lambdaQueryWrapper.eq(Department::getType,1);
        List<Department> departmentList = departmentService.list(lambdaQueryWrapper);
        return Result.buildSuccess(departmentList);
    }

    @RequestMapping(value = "/searchConList",method = RequestMethod.GET)
    public Result searchConList(@RequestParam Long depId){
        QueryWrapper lambdaQueryWrapper= QueryWrapper.create().from(ConsultingRoom.class);
        lambdaQueryWrapper.eq(ConsultingRoom::getDepId,depId);
        List<ConsultingRoom> consultingRoomList = consultingRoomService.list(lambdaQueryWrapper);
        return Result.buildSuccess(consultingRoomList);
    }


    @RequestMapping(value = "/searchCanRegisterInDateRange",method = RequestMethod.POST)
    public Result searchCanRegisterInDateRange(@RequestBody WorkPlanInQueryDto workPlanInQueryDto) {
        List<Map> mapList = doctorWorkPlanService.searchCanRegisterInDateRange(workPlanInQueryDto);
        return Result.buildSuccess(mapList);
    }

    @RequestMapping(value = "/searchDeptSubDoctorPlanInDay",method = RequestMethod.POST)
    public Result searchDeptSubDoctorPlanInDay(@RequestBody WorkPlanScheduleQueryDto workPlanScheduleQueryDto) {
        List<WorkPlanDateVo> workPlanDateVos = doctorWorkPlanService.searchDeptSubDoctorPlanInDay(workPlanScheduleQueryDto);
        return Result.buildSuccess(workPlanDateVos);
    }


    @RequestMapping(value = "/searchDoctorInfoById",method = RequestMethod.GET)
    public Result searchDoctorInfoById(Long doctorId) {
        DoctorVo doctorVo = doctorService.findDoctorById(doctorId);
        return Result.buildSuccess(doctorVo);
    }

    @RequestMapping(value = "/searchDoctorWorkPlanSchedule",method = RequestMethod.POST)
    public Result searchDoctorWorkPlanSchedule(@RequestBody DoctorScheduleQueryDto doctorScheduleQueryDto) {
        List<DoctorScheduleVo> list = doctorWorkPlanService.searchDoctorWorkPlanSchedule(doctorScheduleQueryDto);
        return Result.buildSuccess(list);
    }



}
