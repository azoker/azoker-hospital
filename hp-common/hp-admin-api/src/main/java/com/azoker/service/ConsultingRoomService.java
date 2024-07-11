package com.azoker.service;

import com.azoker.pojo.dto.ConsultingRoomQueryDto;
import com.azoker.pojo.entity.ConsultingRoom;
import com.azoker.pojo.vo.ConsultingRoomListVo;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * Created by zxd on 2023/7/5
 */
public interface ConsultingRoomService extends IService<ConsultingRoom> {

    /**
     * 条件查询诊室列表
     * @param consultingRoomQueryDto
     * @return
     */
    List<ConsultingRoomListVo> findConsultingRoomByQueryDto(ConsultingRoomQueryDto consultingRoomQueryDto);


    /**
     * 查询指定科室下的诊室
     * @param depId
     * @return
     */
    List<ConsultingRoom> findConByDepId(Long depId);

}
