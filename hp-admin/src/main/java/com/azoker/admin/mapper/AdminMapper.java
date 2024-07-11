package com.azoker.admin.mapper;

import com.azoker.pojo.dto.AdminQueryDto;
import com.azoker.pojo.entity.Admin;
import com.azoker.pojo.vo.AdminListVo;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * Created by zxd on 2023/7/10
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据条件查询管理员列表
     * @param adminQueryDto
     * @return
     */
    List<AdminListVo> findAdminByQueryDto(AdminQueryDto adminQueryDto);

}
