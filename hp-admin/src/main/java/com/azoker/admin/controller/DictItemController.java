package com.azoker.admin.controller;
import com.azoker.pojo.dto.BaseDeleteDto;
import com.azoker.pojo.dto.DictItemQueryDto;
import com.azoker.pojo.entity.DictItem;
import com.azoker.pojo.entity.DictType;
import com.azoker.pojo.vo.DictItemListVo;
import com.azoker.result.Result;
import com.azoker.service.DictItemService;
import com.azoker.service.DictTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Created by zxd on 2023/03/26
 */
@RequestMapping("/dictItem")
@Controller
@ResponseBody
public class DictItemController {


    @Autowired
    private DictItemService dictItemService;

    @Autowired
    private DictTypeService dictTypeService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result list(@RequestBody DictItemQueryDto dictItemQueryDto){
        PageHelper.startPage(dictItemQueryDto.getPage(),dictItemQueryDto.getLimit());
        //查询页面数据
        List<DictItemListVo> dictItemListVoList = dictItemService.findDictItemByQueryDto(dictItemQueryDto);
        //查询总记录数
        PageInfo<DictItemListVo> pageInfo = new PageInfo<>(dictItemListVoList);
        return Result.buildSuccess(dictItemListVoList,pageInfo.getTotal());
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") Long id){
        DictItem dictItem = dictItemService.getById(id);
        return Result.buildSuccess(dictItem);
    }

    @RequestMapping(value = "/findDictItemByCode",method = RequestMethod.GET)
    public Result findDictItemByDictTypeCode(@RequestParam String code){
        List<DictItem> itemList = dictItemService.findDictItemByDictTypeCode(code);
        return Result.buildSuccess(itemList);
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody DictItem dictItem){

        //查询字典类型是否存在
        DictType dictType = dictTypeService.getById(dictItem.getDicTypeId());
        if(dictType==null){
           return Result.buildFail(20002,"字典类型不存在!");
        }

        dictItemService.save(dictItem);
        return Result.buildSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody DictItem dictItem){

        //查询字典类型是否存在
        DictType dictType = dictTypeService.getById(dictItem.getDicTypeId());
        if(dictType==null){
            return Result.buildFail(20002,"字典类型不存在!");
        }

        dictItemService.updateById(dictItem);
        return Result.buildSuccess();
    }


    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public Result deleteById(@RequestParam Long id){
        dictItemService.removeById(id);
        return  Result.buildSuccess();
    }


    @RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
    public Result deleteByIds(@RequestBody BaseDeleteDto baseDeleteDto){
        dictItemService.removeByIds(baseDeleteDto.getIds());
        return  Result.buildSuccess();
    }
}
