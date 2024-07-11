package com.azoker.admin.controller;



import com.azoker.admin.interceptor.AdminThreadLocal;
import com.azoker.pojo.dto.MenuDTO;
import com.azoker.pojo.dto.RoutersMenuDto;
import com.azoker.pojo.entity.Menu;
import com.azoker.result.Result;
import com.azoker.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zxd on 2022/3/2
 */
@Api(tags = "菜单权限接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 添加菜单
     * @param menu
     */
    @ApiOperation(value = "添加菜单", notes = "添加菜单", httpMethod = "POST")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result save(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.buildSuccess();
    }

    /**
     * 获取菜单树
     */
    @ApiOperation(value = "获取菜单树", notes = "获取菜单树", httpMethod = "GET")
    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    public Result getMenuTree() {
        //超级管理员查询所有菜单
        Long adminId= AdminThreadLocal.get().getUsername().equals("admin")?0L:AdminThreadLocal.get().getId();
        List<Menu> menuList = menuService.selectMenuTree(adminId);
        return Result.buildSuccess(menuList);
    }


    /**
     * 获取所有菜单
     * @return
     */
    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单", httpMethod = "GET")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result getMenus() {
        //设置为0时，service不会走条件查询，会查询所有菜单
        List<Menu> menuList = menuService.selectMenuTree(0L);
        return Result.buildSuccess(menuList);
    }


    /**
     * 获取路由
     * @return
     */
    @ApiOperation(value = "获取路由", notes = "获取路由", httpMethod = "GET")
    @RequestMapping(value = "getRouters",method = RequestMethod.GET)
    public Result getRouters() {
        //超级管理员获取所有路由
        Long adminId=AdminThreadLocal.get().getUsername().equals("admin")?0L:AdminThreadLocal.get().getId();
        List<Menu> menuList = menuService.selectMenuTree(adminId);
        return Result.buildSuccess(RoutersMenuDto.buildMenus(menuList));
    }

    /**
     * 修改菜单
     * @param menuDto
     * @return
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单", httpMethod = "POST")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result updateMenu(@RequestBody MenuDTO menuDto) {
        menuService.updateMenuById(menuDto);
        return Result.buildSuccess();
    }

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除菜单", notes = "根据id删除菜单", httpMethod = "GET")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public Result deleteMenu(@PathVariable("id") Long id) {
         menuService.removeMenuById(id);
        return Result.buildSuccess();
    }



    /**
     * 根据角色ID获取其所有权限ID
     * @param id role id
     */
    @ApiOperation(value = "根据角色ID获取其所有权限ID", notes = "根据角色ID获取其所有权限ID", httpMethod = "GET")
    @RequestMapping(value = "getRoleHasMenu",method = RequestMethod.GET)
    public Result getRoleHasMenu(Long id) {
        List<Long> allMenu = menuService.getMenuByRoleId(id);
        return Result.buildSuccess(allMenu);
    }
}

