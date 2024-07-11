package com.azoker.admin.controller;

import com.azoker.pojo.dto.BaseDeleteDto;
import com.azoker.pojo.dto.ConsultingRoomQueryDto;
import com.azoker.pojo.entity.ConsultingRoom;
import com.azoker.pojo.vo.ConsultingRoomListVo;
import com.azoker.result.Result;
import com.azoker.service.ConsultingRoomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxd on 2023/7/5
 */
@RequestMapping("/consultingRoom")
@RestController
@Slf4j
public class ConsultingRoomController {


    @Autowired
    private ConsultingRoomService consultingRoomService;


    @PostMapping(value = "/list")
    public Result list(@RequestBody ConsultingRoomQueryDto consultingRoomQueryDto) {
        //1.组装分页条件
        PageHelper.startPage(consultingRoomQueryDto.getPage(),consultingRoomQueryDto.getLimit());
        //2.查询结果
        List<ConsultingRoomListVo> consultingRoomListVoList = consultingRoomService.findConsultingRoomByQueryDto(consultingRoomQueryDto);
        //3.获取分页信息
        PageInfo<ConsultingRoomListVo> pageInfo=new PageInfo<>(consultingRoomListVoList);

        return Result.buildSuccess(consultingRoomListVoList,pageInfo.getTotal());
    }

    @RequestMapping(value = "/searchConList",method = RequestMethod.GET)
    public Result searchConList(@RequestParam Long depId){
        QueryWrapper lambdaQueryWrapper= QueryWrapper.create().from(ConsultingRoom.class);
        lambdaQueryWrapper.eq(ConsultingRoom::getDepId,depId);
        List<ConsultingRoom> consultingRoomList = consultingRoomService.list(lambdaQueryWrapper);
        return Result.buildSuccess(consultingRoomList);
    }


    @GetMapping(value = "/findAll")
    public Result findAll() {
        List<ConsultingRoom> consultingRoomList = consultingRoomService.list();
        return Result.buildSuccess(consultingRoomList);
    }

    @GetMapping(value = "/{id}")
    public Result find(@PathVariable("id") Long id) {
        ConsultingRoom consultingRoom = consultingRoomService.getById(id);
        return Result.buildSuccess(consultingRoom);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody ConsultingRoom consultingRoom) {
        boolean result = consultingRoomService.save(consultingRoom);
        return Result.judge(result);
    }


    @PostMapping(value = "/update")
    public Result update(@RequestBody ConsultingRoom consultingRoom) {
        boolean result = consultingRoomService.updateById(consultingRoom);

        Map<String,String> param=new HashMap();
        param.put("depId",consultingRoom.getDepId()+"");
        log.info("admin调用删除缓存的接口....{}",consultingRoom.getDepId());

        return Result.judge(result);
    }


    @GetMapping(value = "/deleteById")
    public Result deleteById(@RequestParam Long id) {
        boolean result = consultingRoomService.removeById(id);
        return Result.judge(result);
    }

    @PostMapping(value = "/deleteByIds")
    public Result deleteByIds(@RequestBody BaseDeleteDto baseDeleteDto) {
        boolean result = consultingRoomService.removeByIds(baseDeleteDto.getIds());
        return Result.judge(result);
    }

}