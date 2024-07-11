package com.azoker.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.azoker.pojo.dto.DepartmentQueryDto;
import com.azoker.pojo.entity.ConsultingRoom;
import com.azoker.pojo.entity.Department;
import com.azoker.result.Result;
import com.azoker.service.ConsultingRoomService;
import com.azoker.service.DepartmentService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by zxd on 2023/7/5
 */
@RequestMapping("/department")
@Controller
@ResponseBody  //返回Json数据
public class DepartmentController {


    @Autowired
    private DepartmentService  departmentService;

    @Autowired
    private ConsultingRoomService consultingRoomService;


    @PostMapping(value = "/list")
    public Result list(@RequestBody DepartmentQueryDto departmentQueryDto){

        //1.组装分页条件
        Page<Department> page=new Page<>(departmentQueryDto.getPage() ,departmentQueryDto.getLimit());

        //2.组装条件查询
        QueryWrapper queryWrapper= QueryWrapper.create().from(Department.class);
        //如果name不等于空，使用name进行模糊查询
        if(!StrUtil.isEmpty(departmentQueryDto.getName())){
            queryWrapper.like("name",departmentQueryDto.getName());
        }

        if(departmentQueryDto.getType()!=null){
           queryWrapper.eq("type",departmentQueryDto.getType());
        }

        if(departmentQueryDto.getRecommended()!=null){
            queryWrapper.eq("recommended",departmentQueryDto.getRecommended());
        }

        //3.查询结果
        Page<Department> departmentPage = departmentService.page(page, queryWrapper);

        return Result.buildSuccess(departmentPage.getRecords(),departmentPage.getTotalRow());
    }


    @GetMapping(value = "/findAll")
    public Result findAll(){

        //1.查询所有数据
        List<Department> departmentList = departmentService.list();

        //2.构建返回结果给前端
        return Result.buildSuccess(departmentList);
    }

    @GetMapping(value = "/{id}")
    public Result find(@PathVariable("id") Long id){

        //1.根据id查询数据
        Department department = departmentService.getById(id);

        //2.构建返回结果给前端
        return Result.buildSuccess(department);
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Department department){
        //1.调用service进行添加
        boolean result = departmentService.save(department);
        //2.封装返回结果
        return  Result.judge(result);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Department department){

        //1.调用service进行修改
        boolean result = departmentService.updateById(department);

        //2.封装返回结果
        return Result.judge(result);
    }


    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public Result deleteById(@RequestParam Long id){

        //根据科室id查询是否有对应的诊室，如果有限制删除

        //根据科室id查询是否有对应的诊室
        QueryWrapper queryWrapper= QueryWrapper.create().from(ConsultingRoom.class);
        queryWrapper.eq("dep_id",id);
        List<ConsultingRoom> consultingRoomList = consultingRoomService.list(queryWrapper);

        if(!CollectionUtils.isEmpty(consultingRoomList)){
            return Result.buildFail(50002,"多的一方关联，限制删除!");
        }
        boolean result = departmentService.removeById(id);
        return Result.judge(result);
    }


}
