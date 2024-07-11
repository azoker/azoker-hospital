package com.azoker.admin.controller;

import com.azoker.admin.permission.Permission;
import com.azoker.pojo.dto.BaseDeleteDto;
import com.azoker.pojo.dto.DoctorQueryDto;
import com.azoker.pojo.entity.Doctor;
import com.azoker.pojo.vo.DoctorListVo;
import com.azoker.result.Result;
import com.azoker.service.DoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by zxd on 2023/7/5
 */
@RequestMapping("/doctor")
@RestController 
public class DoctorController {


    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/findDoctorByConId")
    public Result findDoctorByConId(@RequestParam Long conId){
        QueryWrapper lambdaQueryWrapper = QueryWrapper.create().from(Doctor.class);
        lambdaQueryWrapper.eq(Doctor::getConId,conId);
        List<Doctor> doctorList = doctorService.list(lambdaQueryWrapper);
        return Result.buildSuccess(doctorList);
    }

    //访问当前方法要求必须具备该注解标记的权限才能访问，没有拦截
    @Permission("doctor:list")
    @PostMapping(value = "/list")
    public Result list(@RequestBody DoctorQueryDto doctorQueryDto) {

        //1.分页条件
        PageHelper.startPage(doctorQueryDto.getPage(),doctorQueryDto.getLimit());

        //2.查询
        List<DoctorListVo> doctorListVoList = doctorService.findDoctorByQueryDto(doctorQueryDto);

        //3.获取分页信息
        PageInfo<DoctorListVo> pageInfo = new PageInfo<>(doctorListVoList);
        return Result.buildSuccess(doctorListVoList,pageInfo.getTotal());
    }


    @GetMapping(value = "/findAll")
    public Result findAll() {
        List<Doctor> doctorList = doctorService.list();
        return Result.buildSuccess(doctorList);
    }

    @GetMapping(value = "/{id}")
    public Result find(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getById(id);
        return Result.buildSuccess(doctor);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Doctor doctor) {
        boolean result = doctorService.save(doctor);
        return Result.judge(result);
    }


    @PostMapping(value = "/update")
    public Result update(@RequestBody Doctor doctor) {
        boolean result = doctorService.updateById(doctor);
        return Result.judge(result);
    }


    @GetMapping(value = "/deleteById")
    public Result deleteById(@RequestParam Long id) {
        boolean result = doctorService.removeById(id);
        return Result.judge(result);
    }

    @PostMapping(value = "/deleteByIds")
    public Result deleteByIds(@RequestBody BaseDeleteDto baseDeleteDto) {
        boolean result = doctorService.removeByIds(baseDeleteDto.getIds());
        return Result.judge(result);
    }

}