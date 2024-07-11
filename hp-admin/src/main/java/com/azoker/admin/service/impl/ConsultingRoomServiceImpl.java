package com.azoker.admin.service.impl;

import com.azoker.admin.mapper.ConsultingRoomMapper;
import com.azoker.pojo.dto.ConsultingRoomQueryDto;
import com.azoker.pojo.entity.ConsultingRoom;
import com.azoker.pojo.vo.ConsultingRoomListVo;
import com.azoker.service.ConsultingRoomService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxd on 2023/7/5
 */
@Service
public class ConsultingRoomServiceImpl extends ServiceImpl<ConsultingRoomMapper, ConsultingRoom> implements ConsultingRoomService {

    @Autowired
    private ConsultingRoomMapper consultingRoomMapper;

    @Override
    public List<ConsultingRoomListVo> findConsultingRoomByQueryDto(ConsultingRoomQueryDto consultingRoomQueryDto) {
        return consultingRoomMapper.findConsultingRoomByQueryDto(consultingRoomQueryDto);
    }

    @Override
    public List<ConsultingRoom> findConByDepId(Long depId) {
        QueryWrapper lambdaQueryWrapper=QueryWrapper.create().from(ConsultingRoom.class);
        lambdaQueryWrapper.eq(ConsultingRoom::getDepId,depId);
        return this.list(lambdaQueryWrapper);
    }
}
