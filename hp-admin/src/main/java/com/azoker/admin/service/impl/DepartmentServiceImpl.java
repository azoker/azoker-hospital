package com.azoker.admin.service.impl;

import com.azoker.admin.mapper.DepartmentMapper;
import com.azoker.pojo.entity.Department;
import com.azoker.service.DepartmentService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxd on 2023/7/4
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public List<Department> findDepartmentAll() {
        //查询门诊的科室
        QueryWrapper lambdaQueryWrapper=QueryWrapper.create().from(Department.class);
        lambdaQueryWrapper.eq(Department::getType,1);
        return  this.list(lambdaQueryWrapper);
    }
}
