package com.azoker.admin.mapper;

import com.azoker.pojo.dto.RoleQueryDto;
import com.azoker.pojo.entity.Role;
import com.azoker.pojo.vo.RoleListVo;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zxd on 2023/7/11
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 角色列表查询
     * @param roleQueryDto
     * @return
     */
    List<RoleListVo> findRoleByQueryDto(RoleQueryDto roleQueryDto);

    /**
     * 根据管理员id查询对应角色
     * @param adminId
     * @return
     */
    @Select("SELECT r.id,r.name,r.description  FROM fr_admin_role ar," +
            "fr_role r WHERE ar.role_id = r.id  AND ar.admin_id =#{adminId}")
    List<Role> findRoleByAdminId(Long adminId);

}
