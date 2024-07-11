package com.azoker.service;


import com.azoker.pojo.dto.MenuDTO;
import com.azoker.pojo.entity.Menu;
import com.mybatisflex.core.service.IService;

import java.util.List;


/**
 * 菜单Service接口
 * Created by zxd on 2022/8/2
 */
public interface MenuService extends IService<Menu> {


    /**
     * 更新菜单信息
     * @param entity
     * @return
     */
     boolean updateMenuById(MenuDTO entity);

    /**
     * 删除菜单信息
     * @param id
     * @return
     */
     void removeMenuById(Long id);

    /**
     * 根据管理员d查找菜单树
     * @return
     */
     List<Menu> selectMenuTree(Long adminId);

    /**
     * 根据id查询菜单
     **/
     Menu getMenuById(Long id);

    /**
     * @Description 根据管理员id查询权限
     **/
     List<String> findPermsByUserId(Long adminId);

    /**
     * 根据角色 id 查询权限
     * @param id
     * @return
     */
     List<Long> getMenuByRoleId(Long id);
}
