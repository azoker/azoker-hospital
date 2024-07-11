package com.azoker.admin.controller;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import com.azoker.pojo.dto.DoctorWorkPlanAddDto;
import com.azoker.pojo.dto.WorkPlanInQueryDto;
import com.azoker.pojo.dto.WorkPlanScheduleQueryDto;
import com.azoker.result.Result;
import com.azoker.service.DoctorWorkPlanService;
import com.azoker.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 * Created by zxd on 2023/7/6
 */
@RequestMapping("/doctorWorkPlan")
@Controller
@ResponseBody
public class DoctorWorkPlanController {

    @Autowired
    private DoctorWorkPlanService doctorWorkPlanService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result addDoctorWorkPlan(@RequestBody @Validated DoctorWorkPlanAddDto doctorWorkPlanAddDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return WebUtils.getResult(bindingResult);
        }
        doctorWorkPlanService.insertDoctorWorkPlan(doctorWorkPlanAddDto);
        return Result.buildSuccess();
    }

    //Vue对应后端实现
    @PostMapping("/searchWorkPlanInRange")
    public Result searchWorkPlanInRange(@RequestBody WorkPlanInQueryDto workPlanInQueryDto) {
        if(StrUtil.isEmpty(workPlanInQueryDto.getEndDate())){
            //如果结束日期为空，默认结束日期为7天后结束日期
            workPlanInQueryDto.setEndDate(DateUtil.offsetDay(DateUtil.parse(workPlanInQueryDto.getStartDate(),
                    "yyyy-MM-dd"), 6).toDateStr());
        }

        //根据开始和结束日期，生成连续的日期
        DateRange range = DateUtil.range(new DateTime(workPlanInQueryDto.getStartDate()), new DateTime(workPlanInQueryDto.getEndDate()), DateField.DAY_OF_MONTH);

        ArrayList dateList = new ArrayList();
        //把连续的日期保存到集合中
        range.forEach(one -> {
            dateList.add(one.toDateStr());
        });
        JSONArray array = doctorWorkPlanService.searchWorkPlanInRange(workPlanInQueryDto, dateList);

        dateList.clear();
        range.reset();
        //往dateList放入格式化的日期，前端用作表头输出
        range.forEach(one -> {
            dateList.add(one.toString("MM月dd日") + "（" + one.dayOfWeekEnum().toChinese() + "）");
        });
        Map map=new HashMap();
        map.put("result",array);
        map.put("dateList",dateList);
        return Result.buildSuccess(map);
    }



    //LayUI对应后端实现
    @RequestMapping(value = "/searchWorkPlanInRangeLayui",method = RequestMethod.GET)
    public Result  searchWorkPlanInRangeLayUi(WorkPlanInQueryDto workPlanInQueryDto){
        if(StrUtil.isEmpty(workPlanInQueryDto.getEndDate())){
            //如果结束日期为空，默认结束日期为7天后结束日期
            workPlanInQueryDto.setEndDate(DateUtil.offsetDay(DateUtil.parse(workPlanInQueryDto.getStartDate(),
                    "yyyy-MM-dd"), 6).toDateStr());
        }
        Collection collection = doctorWorkPlanService.searchWorkPlanInRange(workPlanInQueryDto);
        return Result.buildSuccess(collection);
    }


    //LayUI对应后端实现
    @RequestMapping(value = "/searchWorkPlanInRangeHeadLayUI",method = RequestMethod.GET)
    public Result  searchWorkPlanInRangeHeadLayUI(WorkPlanInQueryDto workPlanInQueryDto){
        if(StrUtil.isEmpty(workPlanInQueryDto.getEndDate())){
            //如果结束日期为空，默认结束日期为7天后结束日期
            workPlanInQueryDto.setEndDate(DateUtil.offsetDay(DateUtil.parse(workPlanInQueryDto.getStartDate(),
                    "yyyy-MM-dd"), 6).toDateStr());
        }
        //根据开始和结束日期，生成连续的日期
        DateRange range = DateUtil.range(new DateTime(workPlanInQueryDto.getStartDate()), new DateTime(workPlanInQueryDto.getEndDate()), DateField.DAY_OF_MONTH);
        List dateList = new ArrayList();

        //把连续的日期保存到集合中
        range.forEach(one -> {
            Map map=new HashMap();
            map.put("field",one.toDateStr());
            map.put("title",one.toString("MM月dd日") + "（" + one.dayOfWeekEnum().toChinese() + "）");
            dateList.add(map);
        });

        return Result.buildSuccess(dateList);
    }



    @RequestMapping(value = "/searchWorkPlanSchedule",method = RequestMethod.POST)
    public Result  searchWorkPlanSchedule(@RequestBody WorkPlanScheduleQueryDto workPlanScheduleQueryDto){
        Collection collection = doctorWorkPlanService.searchConSchedule(workPlanScheduleQueryDto);
        return Result.buildSuccess(collection);
    }


}
