package com.azoker.admin.controller;


import com.azoker.pojo.dto.RoleQueryDto;
import com.azoker.pojo.dto.UpdateRoleMenuDto;
import com.azoker.pojo.dto.UpdateRolePermissionDto;
import com.azoker.pojo.entity.Role;
import com.azoker.pojo.vo.RoleListVo;
import com.azoker.result.Result;
import com.azoker.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zxd on 2023/03/26
 */
@RequestMapping("/role")
@RestController
@Api(tags = "角色相关接口")
public class RoleController {
  
        @Autowired
        private RoleService roleService;

        /**
        * 分页查询所有数据
        * @return 所有数据
        */
        @RequestMapping(value = "/list",method = RequestMethod.POST)
        @ApiOperation(value = "角色列表接口",httpMethod = "POST")
        public Result list(@RequestBody RoleQueryDto roleQueryDto) {

            //1.分页条件
            PageHelper.startPage(roleQueryDto.getPage(),roleQueryDto.getLimit());

            //2.查询
            List<RoleListVo> list =  roleService.findRoleByQueryDto(roleQueryDto);

            //3.分页结果
            PageInfo<RoleListVo> pageInfo = new PageInfo<>(list);
            return Result.buildSuccess(list,pageInfo.getTotal());
        }




        /**
        * 查询所有数据
        * @return 所有数据
        */
        @RequestMapping(value = "/findAll",method = RequestMethod.GET)
        @ApiOperation(value = "查询所有角色",httpMethod = "GET")
        public Result selectAll() {
         List<Role> list =  roleService.list();
         return Result.buildSuccess(list);
        }
        
        /**
        * 通过主键查询单条数据
        * @param id 主键
        * @return 单条数据
        */
        @RequestMapping(value = "/{id}",method = RequestMethod.GET)
        @ApiOperation(value = "根据id查询角色",httpMethod = "GET")
        public Result find(@PathVariable("id") Long id) {
            return Result.buildSuccess( roleService.getById(id));
        }


    /**
     * 修改角色的权限
     * @return 新增结果
     */
    @RequestMapping(value = "/updateRolePermission",method = RequestMethod.POST)
        @ApiOperation(value = "修改角色的权限",httpMethod = "POST")
        public Result updateRolePermission(@RequestBody UpdateRolePermissionDto updateRolePermissionDto) {
            roleService.updateRolePermission(updateRolePermissionDto);
            return Result.buildSuccess();
        }

        @RequestMapping(value = "/updateRoleMenu",method = RequestMethod.POST)
        @ApiOperation(value = "修改角色的权限",httpMethod = "POST")
        public Result updateRoleMenu(@RequestBody UpdateRoleMenuDto updateRoleMenuDto) {
            roleService.updateRoleMenu(updateRoleMenuDto);
            return Result.buildSuccess();
        }

        /**
        * 新增数据
        * @return 新增结果
        */
        @RequestMapping(value = "/add",method = RequestMethod.POST)
        @ApiOperation(value = "添加角色",httpMethod = "POST")
        public Result add(@RequestBody Role role) {
            roleService.save(role);
            return Result.buildSuccess();
        }
        

        
        /**
        * 修改数据
        * @param role 实体对象
        * @return 修改结果
        */
        @RequestMapping(value = "/update",method = RequestMethod.POST)
        public Result update(@RequestBody Role role) {
             roleService.updateById(role);
             return Result.buildSuccess();
        }

        /**
        * 删除数据
        * @param id 主键
        */
        @RequestMapping(value = "/delete",method = RequestMethod.GET)
        public Result delete(Long id) {
            roleService.removeById(id);
            return Result.buildSuccess();
        }


        @RequestMapping(value = "/findRoleByAdminId",method = RequestMethod.GET)
        public Result findRoleByAdminId(@RequestParam Long adminId){
            List<Role> roleList = roleService.findRoleByAdminId(adminId);
            return Result.buildSuccess(roleList);
        }



}

