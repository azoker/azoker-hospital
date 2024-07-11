package com.azoker.pojo.dto;

import com.azoker.pojo.entity.Menu;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zxd on 2022/8/3
 */
@Data
public class RoutersMenuDto {

    private String name;
    private String path;
    private String redirect;
    private String component;
    //private Boolean alwaysShow;
    private RoutersMenuMetaDto meta;
    private List<RoutersMenuDto> children;


    public static  List<RoutersMenuDto> buildMenus(List<Menu> menuList) {
        
        //Vue路由
        List<RoutersMenuDto> list = new LinkedList<RoutersMenuDto>();

        menuList.forEach(menu -> {
                    if (menu != null) {

                        //获取子路由
                        List<Menu> menuDTOList = menu.getChildren();

                        RoutersMenuDto routersMenuDto = new RoutersMenuDto();
                        routersMenuDto.setName(menu.getName());
                        routersMenuDto.setPath(menu.getPath());
                        
                        // 如果不是外链
                        if (menu.getIsFrame()) {

                            if (menu.getParentId().equals(0L)) {
                                //一级目录需要加斜杠，不然访问 会跳转404页面
                                routersMenuDto.setPath("/" + menu.getPath());
                                routersMenuDto.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
                            } else if (!StringUtils.isEmpty(menu.getComponent())) {
                                routersMenuDto.setComponent(menu.getComponent());
                            }

                        }

                        //封装 meta: { title: '系统管理', icon: 'el-icon-setting' }
                        routersMenuDto.setMeta(new RoutersMenuMetaDto(menu.getName(), menu.getIcon()));

                        if (menuDTOList != null && menuDTOList.size() != 0 && menu.getType() == 0) {

                            //递归处理路由
                            routersMenuDto.setChildren(buildMenus(menuDTOList));


                        } else if (menu.getParentId().equals(0L)) {
                            //处理是一级菜单并且没有子菜单的情况
                           // routersMenuDto.setAlwaysShow(false);
                            routersMenuDto.setRedirect("noredirect");
                            RoutersMenuDto routersMenuDto1 = new RoutersMenuDto();
                            routersMenuDto1.setMeta(routersMenuDto.getMeta());
                            // 非外链
                            if (menu.getIsFrame()) {
                                routersMenuDto1.setPath("index");
                                routersMenuDto1.setName(routersMenuDto.getName());
                                routersMenuDto1.setComponent(routersMenuDto.getComponent());
                            } else {
                                routersMenuDto1.setPath(menu.getPath());
                            }
                            routersMenuDto.setName(null);
                            routersMenuDto.setMeta(null);
                            routersMenuDto.setComponent("Layout");
                            List<RoutersMenuDto> list1 = new ArrayList<>();
                            list1.add(routersMenuDto1);
                            routersMenuDto.setChildren(list1);
                        }
                        list.add(routersMenuDto);
                    }
                }
        );

        list.forEach(routersMenuDto->{
            if(!CollectionUtils.isEmpty(routersMenuDto.getChildren())){
             routersMenuDto.setRedirect(routersMenuDto.getPath()+"/"+routersMenuDto.getChildren().get(0).getPath());
            }
        });

        return list;
    }

}
