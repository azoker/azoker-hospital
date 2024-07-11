package com.azoker.service;

import com.azoker.pojo.dto.RoleQueryDto;
import com.azoker.pojo.dto.UpdateRoleMenuDto;
import com.azoker.pojo.dto.UpdateRolePermissionDto;
import com.azoker.pojo.entity.Role;
import com.azoker.pojo.vo.RoleListVo;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * Created by zxd on 2023/7/11
 */
public interface RoleService extends IService<Role> {

    /**
     * 角色列表查询
     * @param roleQueryDto
     * @return
     */
    List<RoleListVo> findRoleByQueryDto(RoleQueryDto roleQueryDto);


    /**
     * 修改角色的权限
     * @param updateRolePermissionDto
     */
    void updateRolePermission(UpdateRolePermissionDto updateRolePermissionDto);



    /**
     * 根据管理员id查询对应角色
     * @param adminId
     * @return
     */
    List<Role> findRoleByAdminId(Long adminId);


    /**
     * 修改角色菜单
     * @param updateRoleMenuDto
     */
    void updateRoleMenu(UpdateRoleMenuDto updateRoleMenuDto);
}
