package com.azoker.admin.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.azoker.admin.mapper.AdminMapper;
import com.azoker.admin.mapper.AdminRoleMapper;
import com.azoker.exception.CustomerException;
import com.azoker.pojo.dto.AdminDto;
import com.azoker.pojo.dto.AdminQueryDto;
import com.azoker.pojo.dto.LoginDto;
import com.azoker.pojo.dto.UpdateAdminRoleDto;
import com.azoker.pojo.entity.Admin;
import com.azoker.pojo.entity.AdminRole;
import com.azoker.pojo.entity.Menu;
import com.azoker.pojo.vo.AdminListVo;
import com.azoker.result.ResultEnum;
import com.azoker.service.AdminService;
import com.azoker.service.MenuService;
import com.azoker.utils.JsonUtils;
import com.azoker.utils.TokenUtils;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by zxd on 2023/7/10
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private MenuService menuService;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean insertAdmin(AdminDto adminDto) {

        //1.校验两次密码输入是否一致
        if(!adminDto.getPassword().equals(adminDto.getPassword2())){
            log.info("两次密码输入不一致");
            throw new CustomerException(ResultEnum.TWO_PASSWORD_ERROR);
        }

        //2.判断用户名，邮箱，电话号码是否重复
        checkAdmin(adminDto);

        //3.拷贝数据到entity
        Admin admin=new Admin();
        BeanUtils.copyProperties(adminDto,admin);


        //4.生成盐
        String salt = UUID.randomUUID().toString();

        //5.密码加密
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String md5Password = md5.digestHex(admin.getPassword()+salt);

        //6.存储盐和加密后的密码
        admin.setPassword(md5Password);
        admin.setSalt(salt);

        //7.保存到数据库
        return this.save(admin);
    }

    @Override
    public String login(LoginDto loginDto) {

        //1.根据用户名查询用户信息
        QueryWrapper lambdaQueryWrapper= QueryWrapper.create().from(Admin.class);
        lambdaQueryWrapper.eq(Admin::getUsername,loginDto.getUsername());
        Admin admin = getMapper().selectOneByQuery(lambdaQueryWrapper);

        if(admin==null){
            log.info("用户不存在:{}",loginDto.getUsername());
            throw new CustomerException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        //2.获取盐
        String salt = admin.getSalt();

        //3.再次进行加密
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String md5Password = md5.digestHex(loginDto.getPassword()+salt);


        //4.使用加密后的密码和服务器存储的加密密码进行对比，如果一致就ok
        if(!admin.getPassword().equals(md5Password)){
            log.info("密码错误:{}",loginDto.getUsername());
            throw new CustomerException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        //5.生成jwt令牌
        Map<String,Object> map=new HashMap<>();
        map.put("id",admin.getId()+"");
        map.put("username",admin.getUsername());
        map.put("name",admin.getName());
        map.put("phone",admin.getPhone());
        map.put("imageUrl",admin.getImageUrl());

        //查询用户对应的权限，存储到jwt中  后期TODO redis进行存储
        List<String> permissionListTags = menuService.findPermsByUserId(admin.getId());
        map.put("permissionListTags", JsonUtils.objectToJson(permissionListTags));

        String token = TokenUtils.createToken(map);

        return token;
    }

    @Override
    public void updateAdminRole(UpdateAdminRoleDto updateAdminRoleDto) {

        //1.先删除管理员的角色
        QueryWrapper queryWrapper=QueryWrapper.create().from(AdminRole.class);
        queryWrapper.eq(AdminRole::getAdminId,updateAdminRoleDto.getAdminId());
        adminRoleMapper.deleteByQuery(queryWrapper);

        //2.获取角色id集合
        List<Long> roleIds = updateAdminRoleDto.getRoleIds();

        //3.判断权限id集合不为空
        if(!CollectionUtils.isEmpty(roleIds)){

            log.debug("往中间表插入数据...");
            roleIds.forEach(roleId->{
                AdminRole adminRole=new AdminRole();
                adminRole.setAdminId(updateAdminRoleDto.getAdminId());
                adminRole.setRoleId(roleId);
                adminRoleMapper.insert(adminRole);
            });

        }

    }

    @Override
    public List<AdminListVo> findAdminByQueryDto(AdminQueryDto adminQueryDto) {
        return adminMapper.findAdminByQueryDto(adminQueryDto);
    }


    private void checkAdmin(AdminDto adminDto) {

        //1.查询用户名是否重复
        QueryWrapper usernameLambdaQueryWrapper=QueryWrapper.create().from(Admin.class);
        usernameLambdaQueryWrapper.eq(Admin::getUsername,adminDto.getUsername());
        if(getMapper().selectOneByQuery(usernameLambdaQueryWrapper)!=null){
            log.info("用户名已经存在：{}",adminDto.getUsername());
            throw new CustomerException(ResultEnum.USERNAME_EXITS);
        }

        //2.查询手机号码是否重复
        QueryWrapper phoneLambdaQueryWrapper=QueryWrapper.create().from(Admin.class);
        phoneLambdaQueryWrapper.eq(Admin::getPhone,adminDto.getPhone());
        if(getMapper().selectOneByQuery(phoneLambdaQueryWrapper)!=null){
            log.info("手机号码已经存在：{}",adminDto.getPhone());
            throw new CustomerException(ResultEnum.PHONE_EXITS);
        }


        //3.查询邮箱是否存在
        QueryWrapper emailLambdaQueryWrapper= QueryWrapper.create().from(Admin.class);
        emailLambdaQueryWrapper.eq(Admin::getEmail,adminDto.getEmail());
        if(getMapper().selectOneByQuery(emailLambdaQueryWrapper)!=null){
            log.info("邮箱已经存在：{}",adminDto.getEmail());
            throw new CustomerException(ResultEnum.EMAIL_EXITS);
        }


    }


}
