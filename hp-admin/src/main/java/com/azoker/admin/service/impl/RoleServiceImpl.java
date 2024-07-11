package com.azoker.admin.service.impl;

import com.azoker.admin.mapper.RoleMapper;
import com.azoker.admin.mapper.RolePermissionMapper;
import com.azoker.pojo.dto.RoleQueryDto;
import com.azoker.pojo.dto.UpdateRoleMenuDto;
import com.azoker.pojo.dto.UpdateRolePermissionDto;
import com.azoker.pojo.entity.Role;
import com.azoker.pojo.entity.RoleMenu;
import com.azoker.pojo.entity.RolePermission;
import com.azoker.pojo.vo.RoleListVo;
import com.azoker.service.RoleMenuService;
import com.azoker.service.RoleService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxd on 2023/7/11
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<RoleListVo> findRoleByQueryDto(RoleQueryDto roleQueryDto) {
        return roleMapper.findRoleByQueryDto(roleQueryDto);
    }

    @Override
    public void updateRolePermission(UpdateRolePermissionDto updateRolePermissionDto) {

        //1.先删除角色对应的权限
        QueryWrapper lambdaQueryWrapper= QueryWrapper.create().from(RolePermission.class);
        lambdaQueryWrapper.eq(RolePermission::getRoleId,updateRolePermissionDto.getRoleId());
        rolePermissionMapper.deleteByQuery(lambdaQueryWrapper);

        //2.获取权限id集合
        List<Long> permissionIds = updateRolePermissionDto.getPermissionIds();


        //3.判断权限id集合不为空
        if(!CollectionUtils.isEmpty(permissionIds)){

            log.debug("王中间表插入数据...roleId:{},permissionIds:{}",
                    updateRolePermissionDto.getRoleId(),updateRolePermissionDto.getPermissionIds());
            permissionIds.forEach(permissionId->{
               RolePermission rolePermission=new RolePermission();
               rolePermission.setRoleId(updateRolePermissionDto.getRoleId());
               rolePermission.setPermissionId(permissionId);
               rolePermissionMapper.insert(rolePermission);
            });

        }


    }


    @Override
    public void updateRoleMenu(UpdateRoleMenuDto updateRoleMenuDto) {
        // 删除当前角色所有菜单
        Long roleId = updateRoleMenuDto.getId();

        QueryWrapper queryWrapper = QueryWrapper.create().from(RoleMenu.class).eq("role_id", roleId);
        roleMenuService.remove(queryWrapper);

        if (updateRoleMenuDto.getMenuIds().size() > 0) {
            // 批量插入 role_menu
            List<Long> menuIds = updateRoleMenuDto.getMenuIds();
            List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
            for (Long menuId : menuIds) {
                roleMenus.add(new RoleMenu(roleId, menuId));
            }
            roleMenuService.saveBatch(roleMenus, roleMenus.size());
        }
    }


    @Override
    public List<Role> findRoleByAdminId(Long adminId) {
        return roleMapper.findRoleByAdminId(adminId);
    }
}
