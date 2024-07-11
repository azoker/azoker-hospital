package com.azoker.admin.controller;


import com.azoker.admin.interceptor.AdminThreadLocal;
import com.azoker.pojo.dto.*;
import com.azoker.pojo.entity.Admin;
import com.azoker.pojo.vo.AdminInfoVo;
import com.azoker.pojo.vo.AdminListVo;
import com.azoker.result.Result;
import com.azoker.service.AdminService;
import com.azoker.utils.WebUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zxd on 2023/07/9
 */
@RequestMapping("/admin")
@RestController
@Api(tags = "管理员相关接口")
@Slf4j
public class AdminController {
  
        @Autowired
        private AdminService adminService;

        /**
        * 分页查询所有数据
        * @return 所有数据
        */
        @PostMapping(value = "/list")
        @ApiOperation(value = "管理员列表接口",httpMethod = "POST")
        public Result list(@RequestBody AdminQueryDto adminQueryDto) {
            //1.设置分页条件
            PageHelper.startPage(adminQueryDto.getPage(),adminQueryDto.getLimit());
            //3.查询
            List<AdminListVo> list =  adminService.findAdminByQueryDto(adminQueryDto);
            PageInfo<AdminListVo> pageInfo = new PageInfo<>(list);
            return Result.buildSuccess(list,pageInfo.getTotal());
        }
        
        /**
        * 查询所有数据
        * @return 所有数据
        */
        @RequestMapping(value = "/findAll",method = RequestMethod.GET)
        @ApiOperation(value = "查询所有管理员",httpMethod = "GET")
        public Result selectAll() {
             List<Admin> list =  adminService.list();
             return Result.buildSuccess(list);
        }
        
        /**
        * 通过主键查询单条数据
        * @param id 主键
        * @return 单条数据
        */
        @RequestMapping(value = "/{id}",method = RequestMethod.GET)
        @ApiOperation(value = "根据id查询管理员",httpMethod = "GET")
        public Result find(@PathVariable("id") Long id) {
            return Result.buildSuccess( adminService.getById(id));
        }
        
        /**
        * 新增数据
        * @return 新增结果
        */
        @RequestMapping(value = "/add",method = RequestMethod.POST)
        @ApiOperation(value = "添加管理员",httpMethod = "POST")
        public Result add(@RequestBody @Validated AdminDto adminDto, BindingResult bindingResult) {
            //1.判断校验是否通过
            if(bindingResult.hasErrors()){
                return WebUtils.getResult(bindingResult);
            }
            //2.添加
            boolean result = adminService.insertAdmin(adminDto);
            return Result.judge(result);
        }


        @RequestMapping(value = "/login",method = RequestMethod.POST)
        @ApiOperation(value = "登录接口",httpMethod = "POST")
        public Result add(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) {
            //1.判断校验是否通过
            if(bindingResult.hasErrors()){
                return WebUtils.getResult(bindingResult);
            }
            //2.添加
            String token = adminService.login(loginDto);
            return Result.buildSuccess(token);
        }


        @ApiOperation(value = "查询用户信息",notes = "查询用户信息",httpMethod = "GET")
        @RequestMapping(value = "/info",method = RequestMethod.GET)
        public  Result info(){
            Admin admin = AdminThreadLocal.get();
            //封装返回的结果
            AdminInfoVo adminInfoVo=new AdminInfoVo();
            adminInfoVo.setName(admin.getName());
            adminInfoVo.setAvatar(admin.getImageUrl());
            return Result.buildSuccess(adminInfoVo);
        }



       /**
        * 修改数据
        * @param admin 实体对象
        * @return 修改结果
        */
        @RequestMapping(value = "/update",method = RequestMethod.POST)
        public Result update(@RequestBody Admin admin) {
             adminService.updateById(admin);
             return Result.buildSuccess();
        }

        /**
        * 删除数据
        * @param id 主键
        */
        @RequestMapping(value = "/delete",method = RequestMethod.GET)
        public Result delete(Long id) {
            adminService.removeById(id);
            return Result.buildSuccess();
        }

        @RequestMapping(value = "/updateAdminRole",method = RequestMethod.POST)
        public Result updateAdminRole(@RequestBody UpdateAdminRoleDto updateAdminRoleDto){
            adminService.updateAdminRole(updateAdminRoleDto);
            return Result.buildSuccess();
        }


}

