package com.azoker.admin.mapper;

import com.azoker.pojo.entity.Menu;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by zxd on 2022/8/3
 */
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 根据管理员id查询权限标记集合
     * @param adminId
     * @return
     */
    @Select("SELECT m.perms FROM fr_menu m,fr_admin_role ar,fr_role_menu rm WHERE ar.role_id = rm.role_id AND rm.menu_id = m.id AND ar.admin_id =#{adminId}")
     List<String> findPermsByUserId(Long adminId);


    /**
     * 根据管理id获取菜单id集合
     * @param adminId
     * @return
     */
    @Select("SELECT m.id FROM fr_menu m,fr_admin_role ar,fr_role_menu rm WHERE ar.role_id = rm.role_id AND rm.menu_id = m.id AND ar.admin_id =#{adminId}")
     List<Long> getMenuIdByAdminId(Long adminId);


    @Select("SELECT m.id FROM fr_menu m,fr_role_menu rm WHERE rm.menu_id = m.id AND rm.role_id =#{id}")
     List<Long> getMenuIdByRoleId(Long id);
}
