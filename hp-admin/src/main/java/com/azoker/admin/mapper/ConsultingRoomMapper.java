package com.azoker.admin.mapper;

import com.azoker.pojo.dto.ConsultingRoomQueryDto;
import com.azoker.pojo.entity.ConsultingRoom;
import com.azoker.pojo.vo.ConsultingRoomListVo;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * Created by zxd on 2023/7/5
 */
public interface ConsultingRoomMapper extends BaseMapper<ConsultingRoom> {


    /**
     * 条件查询诊室列表
     * @param consultingRoomQueryDto
     * @return
     */
    List<ConsultingRoomListVo> findConsultingRoomByQueryDto(ConsultingRoomQueryDto consultingRoomQueryDto);

}
