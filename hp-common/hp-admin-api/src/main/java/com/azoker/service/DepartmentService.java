package com.azoker.service;

import com.azoker.pojo.entity.Department;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * Created by zxd on 2023/7/4
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 查询所有门诊科室
     * @return
     */
    List<Department> findDepartmentAll();



}
