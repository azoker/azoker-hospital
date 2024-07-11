package com.azoker.service;

import com.azoker.pojo.dto.AdminDto;
import com.azoker.pojo.dto.AdminQueryDto;
import com.azoker.pojo.dto.LoginDto;
import com.azoker.pojo.dto.UpdateAdminRoleDto;
import com.azoker.pojo.entity.Admin;
import com.azoker.pojo.vo.AdminListVo;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * Created by zxd on 2023/7/10
 */
public interface AdminService extends IService<Admin> {

    /**
     * 添加管理员
     * @param adminDto
     * @return
     */
    boolean insertAdmin(AdminDto adminDto);

    /**
     * 登录
     * @param loginDto
     * @return  生成的jwt令牌
     */
    String login(LoginDto loginDto);



    /**
     * 修改管理员角色
     * @param updateAdminRoleDto
     */
    void updateAdminRole(UpdateAdminRoleDto updateAdminRoleDto);


    /**
     * 根据条件查询管理员列表
     * @param adminQueryDto
     * @return
     */
    List<AdminListVo> findAdminByQueryDto(AdminQueryDto adminQueryDto);

}
