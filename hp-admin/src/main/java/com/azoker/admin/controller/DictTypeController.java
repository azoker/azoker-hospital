package com.azoker.admin.controller;


import com.azoker.pojo.dto.BasePageDto;
import com.azoker.pojo.entity.DictType;
import com.azoker.result.Result;
import com.azoker.service.DictTypeService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by zxd on 2023/03/26
 */
@RequestMapping("/dictType")
@RestController
@Api(tags = "字典类型相关接口")
public class DictTypeController{
  
        @Autowired
        private DictTypeService dictTypeService;

        /**
        * 分页查询所有数据
        * @return 所有数据
        */
        @RequestMapping(value = "/list",method = RequestMethod.POST)
        @ApiOperation(value = "字典类型列表接口",httpMethod = "POST")
        public Result list(@RequestBody BasePageDto basePageDto) {
            Page<DictType> page=new Page<>(basePageDto.getPage() ,basePageDto.getLimit());
            Page<DictType> dictTypePage = dictTypeService.page(page);
            return Result.buildSuccess(dictTypePage.getRecords(),dictTypePage.getTotalRow());
        }
        
        /**
        * 查询所有数据
        * @return 所有数据
        */
        @RequestMapping(value = "/findAll",method = RequestMethod.GET)
        @ApiOperation(value = "查询所有字典类型",httpMethod = "GET")
        public Result selectAll() {
         List<DictType> list =  dictTypeService.list();
         return Result.buildSuccess(list);
        }
        
        /**
        * 通过主键查询单条数据
        * @param id 主键
        * @return 单条数据
        */
        @RequestMapping(value = "/{id}",method = RequestMethod.GET)
        @ApiOperation(value = "根据id查询字典",httpMethod = "GET")
        public Result find(@PathVariable("id") Long id) {
            return Result.buildSuccess( dictTypeService.getById(id));
        }
        
        /**
        * 新增数据
        * @return 新增结果
        */
        @RequestMapping(value = "/add",method = RequestMethod.POST)
        @ApiOperation(value = "添加字典",httpMethod = "POST")
        public Result add(@RequestBody DictType dictType) {
            dictTypeService.save(dictType);
            return Result.buildSuccess();
        }
        

        
        /**
        * 修改数据
        * @param dictType 实体对象
        * @return 修改结果
        */
        @RequestMapping(value = "/update",method = RequestMethod.POST)
        public Result update(@RequestBody DictType dictType) {
             dictTypeService.updateById(dictType);
             return Result.buildSuccess();
        }

        /**
        * 删除数据
        * @param id 主键
        */
        @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
        public Result delete(Long id) {
            dictTypeService.removeById(id);
            return Result.buildSuccess();
        }



}

