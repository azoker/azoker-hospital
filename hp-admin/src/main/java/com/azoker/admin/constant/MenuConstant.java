package com.azoker.admin.constant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜单常量
 * Created by zxd on 2022/8/3
 */
public class MenuConstant {

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 按钮标记
     */
    public enum ButtonMarking{

        ADD("add"),
        UPDATE("update"),
        DELETE("delete"),
        FIND("find"),
        EXPORT("export");

        private String marking;

        private ButtonMarking(String marking){
            this.marking=marking;
        }

        public String getButtonMarking(){
            return marking;
        }

        /**
         * 获取所有的按钮标记
         * @return
         */
        public List<String>  getButtonMarkingList(){
         return Stream.of(ButtonMarking.values()).map(buttonMarking -> buttonMarking.getButtonMarking()).collect(Collectors.toList());
        }

    }

}
