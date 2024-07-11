package com.azoker.admin.mapper;

import com.azoker.pojo.dto.DictItemQueryDto;
import com.azoker.pojo.entity.DictItem;
import com.azoker.pojo.vo.DictItemListVo;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * Created by zxd on 2023/3/26
 */
public interface DictItemMapper extends BaseMapper<DictItem> {


    /**
     * 带条件的多表分页查询
     * @param dictItemQueryDto
     */
    List<DictItemListVo> findDictItemByQueryDto(DictItemQueryDto dictItemQueryDto);

    /**
     * 根据字典类型编号查询对应的字典
     * @param code  字典类型编号
     * @return
     */
    List<DictItem> findDictItemByDictTypeCode(String code);

}
