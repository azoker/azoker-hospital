package com.azoker.admin.service.impl;

import com.azoker.admin.constant.MenuConstant;
import com.azoker.admin.mapper.MenuMapper;
import com.azoker.exception.CustomerException;
import com.azoker.pojo.dto.MenuDTO;
import com.azoker.pojo.entity.Menu;
import com.azoker.result.ResultEnum;
import com.azoker.service.MenuService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单Service实现
 * Created by zxd on 2022/8/3
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private Logger log= LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public boolean save(Menu menu) {
        // 菜单校验
        verifyForm(menu);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(menu.getUpdateTime());
        return super.save(menu);
    }

    @Override
    public boolean updateMenuById(MenuDTO entity) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(entity, menu);
        // 菜单校验
        verifyForm(menu);
        menu.setUpdateTime(new Date());
        return this.updateById(menu);
    }

    @Override
    public List<Menu> selectMenuTree(Long adminId) {

       QueryWrapper menuLambdaQueryWrapper = QueryWrapper.create().from(Menu.class);

        //设置查询显示的字段
        menuLambdaQueryWrapper.select(Menu::getId, Menu::getName, Menu::getPerms, Menu::getPath, Menu::getParentId, Menu::getComponent, Menu::getIsFrame, Menu::getIcon, Menu::getSort, Menu::getType, Menu::getDelFlag);


        if (adminId != 0L) {
            //查询对应用户所有的权限
            List<Long> menuIdList = menuMapper.getMenuIdByAdminId(adminId);
            menuLambdaQueryWrapper.in(Menu::getId, menuIdList);
        }

        //封装一级目录
        List<Menu> menuList = new ArrayList<>();

        //根据条件查询菜单信息
        List<Menu> menus = getMapper().selectListByQuery(menuLambdaQueryWrapper);

        menus.forEach(menu -> {
            //判断是否是一级菜单
            if (menu.getParentId() == null || menu.getParentId() == 0L) {
                //一级
                menu.setLevel(0);

                //判断该一级菜单是否存在，如果不存在在添加
                if (existsMenu(menuList, menu)) {
                    menuList.add(menu);
                }
            }
        });

        //对一级菜单按照顺序进行排序
        menuList.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));

        //查找一级菜单子菜单
        findChildren(menuList, menus);

        return menuList;
    }



    /**
     * 判断菜单是否存在
     * @param menuList
     * @param menu
     * @return
     */
    public boolean existsMenu(List<Menu> menuList, Menu menu) {
        boolean exist = false;
        for (Menu m : menuList) {
            if (m.getId().equals(menu.getId())) {
                exist = true;
            }
        }
        return !exist;
    }

    /**
     * 遍历菜单
     * @param menuList
     * @param menus
     */
    //menuList 一级目录   menus所有菜单
    //menuList  二级菜单  menus所有菜单
    public void findChildren(List<Menu> menuList, List<Menu> menus) {

        //遍历所一级目录
        for (Menu menu : menuList) {

            //准备子菜单集合
            List<Menu> children = new ArrayList<>();

            //从查询到的菜单中查找子菜单
            for (Menu m : menus) {

                //判断是否按钮，如果是按钮直接跳过
                if (menu.getType() == 2) {
                    continue;
                }

                //如果当前遍历的子菜单的父级id是一级菜单  则加入该一级菜单的子集合中
                if (menu.getId() != null && menu.getId().equals(m.getParentId())) {
                    //设置父菜单名字
                    m.setParentName(menu.getName());
                    //设置菜单登记
                    m.setLevel(menu.getLevel() + 1);
                    //如果不存在该菜单则进行添加操作
                    if (existsMenu(children, m)) {
                        children.add(m);
                    }
                }
            }

            //将子菜单加入一级菜单中
            menu.setChildren(children);

            //子菜单排序
            children.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));

            //继续递归查找
            findChildren(children, menus);
        }

    }



    @Override
    public Menu getMenuById(Long id) {
        QueryWrapper queryWrapper=QueryWrapper.create().from(Menu.class);
        queryWrapper.select(Menu::getType).eq(Menu::getId, id);
        return getMapper().selectOneByQuery(queryWrapper);
    }

    @Override
    public List<String> findPermsByUserId(Long adminId) {
        return menuMapper.findPermsByUserId(adminId);
    }

    @Override
    public List<Long> getMenuByRoleId(Long id) {
        return menuMapper.getMenuIdByRoleId(id);
    }

    @Override
    public void removeMenuById(Long id) {
        QueryWrapper queryWrapper=QueryWrapper.create().from(Menu.class);
        queryWrapper.eq(Menu::getParentId, id);

        List<Long> idList = this.list(queryWrapper).stream().map(Menu::getId).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(idList)) {
            log.error("parentId :{},has children,not delete",id);
            throw new CustomerException(ResultEnum.NO_DATA);
        }
        this.removeById(id);
    }


    /**
     * 校验菜单参数是否正确
     * @param menu
     */
    private void verifyForm(Menu menu) {

        //上级菜单类型
        int parentType = MenuConstant.MenuType.CATALOG.getValue();

        if (menu.getParentId() != 0) {
            //查找父菜单
            Menu parentMenu = getMenuById(menu.getParentId());
            //获取父菜单类型
            parentType = parentMenu.getType();
        }

        //目录、菜单的上级菜单只能是目录
        if (menu.getType() == MenuConstant.MenuType.CATALOG.getValue() || menu.getType() == MenuConstant.MenuType.MENU.getValue()) {
            if (parentType != MenuConstant.MenuType.CATALOG.getValue()) {
                log.error("catalog or menu parent is only catalog");
                throw new CustomerException(ResultEnum.NO_DATA);
            }
            return;
        }

        //按钮的上级只能是菜单
        if (menu.getType() == MenuConstant.MenuType.BUTTON.getValue()) {
            if (parentType != MenuConstant.MenuType.MENU.getValue()) {
                log.error("button parent is only menu");
                throw new CustomerException(ResultEnum.NO_DATA);
            }
        }
    }
}
