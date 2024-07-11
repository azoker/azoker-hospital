package com.azoker.admin.service.impl;

import com.azoker.admin.mapper.DictItemMapper;
import com.azoker.pojo.dto.DictItemQueryDto;
import com.azoker.pojo.entity.DictItem;
import com.azoker.pojo.vo.DictItemListVo;
import com.azoker.service.DictItemService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zxd on 2023/3/26
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {

    @Autowired
    private DictItemMapper dictItemMapper;

    @Override
    public List<DictItemListVo> findDictItemByQueryDto(DictItemQueryDto dictItemQueryDto) {
        return dictItemMapper.findDictItemByQueryDto(dictItemQueryDto);
    }

    @Override
    public List<DictItem> findDictItemByDictTypeCode(String code) {
        return dictItemMapper.findDictItemByDictTypeCode(code);
    }
}
