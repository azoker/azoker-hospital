package com.azoker.admin.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.azoker.admin.interceptor.AdminThreadLocal;
import com.azoker.admin.mapper.DoctorWorkPlanMapper;
import com.azoker.admin.mapper.DoctorWorkPlanScheduleMapper;
import com.azoker.exception.CustomerException;
import com.azoker.pojo.dto.DoctorScheduleQueryDto;
import com.azoker.pojo.dto.DoctorWorkPlanAddDto;
import com.azoker.pojo.dto.WorkPlanInQueryDto;
import com.azoker.pojo.dto.WorkPlanScheduleQueryDto;
import com.azoker.pojo.entity.DoctorWorkPlan;
import com.azoker.pojo.entity.DoctorWorkPlanSchedule;
import com.azoker.pojo.vo.DoctorScheduleVo;
import com.azoker.pojo.vo.DoctorWorkPlanVo;
import com.azoker.pojo.vo.WorkPlanDateVo;
import com.azoker.pojo.vo.WorkPlanScheduleVo;
import com.azoker.result.ResultEnum;
import com.azoker.service.DoctorWorkPlanService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zxd on 2023/3/31
 */
@Service
@Slf4j
public class DoctorWorkPlanServiceImpl extends ServiceImpl<DoctorWorkPlanMapper, DoctorWorkPlan> implements DoctorWorkPlanService {

    @Autowired
    private DoctorWorkPlanMapper doctorWorkPlanMapper;

    @Autowired
    private DoctorWorkPlanScheduleMapper doctorWorkPlanScheduleMapper;

    @Override
    public void insertDoctorWorkPlan(DoctorWorkPlanAddDto doctorWorkPlanAddDto) {

        //1.查询医生是否存在出诊计划
        QueryWrapper lambdaQueryWrapper= QueryWrapper.create().from(DoctorWorkPlan.class);
        lambdaQueryWrapper.eq(DoctorWorkPlan::getDoctorId,doctorWorkPlanAddDto.getDoctorId()).
                eq(DoctorWorkPlan::getDate,DateUtil.date(doctorWorkPlanAddDto.getDate()).toDateStr());
        if(!ObjectUtil.isEmpty(this.getOne(lambdaQueryWrapper))){
            log.debug("{}:医生，{}:存在出诊计划...",doctorWorkPlanAddDto.getDoctorId(),doctorWorkPlanAddDto.getDate());
            throw new CustomerException(ResultEnum.DOCTOR_EXITS);
        }

        //判断排班时间是否合法
        if(doctorWorkPlanAddDto.getDate().getTime()<new Date().getTime()){
            log.debug("排班时间不能小于当前时间");
           throw new CustomerException(ResultEnum.PLANT_TIME_ERROR);
        }

        //2.准备出诊计划entity
        DoctorWorkPlan doctorWorkPlan=new DoctorWorkPlan();
        BeanUtils.copyProperties(doctorWorkPlanAddDto,doctorWorkPlan);
        doctorWorkPlan.setNum(0);
        //3.设置当天最大挂号人数
        doctorWorkPlan.setMaxNum(doctorWorkPlanAddDto.getSlots().length*doctorWorkPlanAddDto.getSlotMaxNum());
        //4.设置操作人id
         doctorWorkPlan.setAdminId(AdminThreadLocal.get().getId());
        //5.保存出诊计划
        this.save(doctorWorkPlan);


        //6.准备出诊时间段数据
        List<DoctorWorkPlanSchedule> doctorWorkPlanList = Arrays.stream(doctorWorkPlanAddDto.getSlots()).map(slot -> {
            DoctorWorkPlanSchedule doctorWorkPlanSchedule = new DoctorWorkPlanSchedule();
            doctorWorkPlanSchedule.setWorkPlanId(doctorWorkPlan.getId());
            doctorWorkPlanSchedule.setMaxNum(doctorWorkPlanAddDto.getSlotMaxNum());
            doctorWorkPlanSchedule.setSlot(slot);
            doctorWorkPlanSchedule.setNum(0);
            return doctorWorkPlanSchedule;
        }).collect(Collectors.toList());

        //7.保存出诊时间段
        doctorWorkPlanScheduleMapper.insertBatch(doctorWorkPlanList);

    }

    @Override
    public Collection searchWorkPlanInRange(WorkPlanInQueryDto workPlanInQueryDto) {

        //1.根据开始和结束日期，生成连续的日期
        DateRange range = DateUtil.range(new DateTime(workPlanInQueryDto.getStartDate()), new DateTime(workPlanInQueryDto.getEndDate()), DateField.DAY_OF_MONTH);
        List dateList = new ArrayList();

        //2.把连续的日期保存到集合中
        range.forEach(one -> {
            dateList.add(one.toDateStr());
        });

        //3.查询出诊数据
        List<DoctorWorkPlanVo> doctorWorkPlanVos = doctorWorkPlanMapper.findDoctorWorkPlanVoByQueryDto(workPlanInQueryDto);

        Long tempConId = null;
        String tempDate = null;
        HashMap tempResult = new HashMap();


        for (DoctorWorkPlanVo doctorWorkPlanVo : doctorWorkPlanVos) {
            String deptName = doctorWorkPlanVo.getDeptName();
            Long conId = doctorWorkPlanVo.getConId();
            String conName = doctorWorkPlanVo.getConName();
            String doctorName = doctorWorkPlanVo.getDoctorName();
            String date = DateUtil.date(doctorWorkPlanVo.getDate()).toDateStr();

            //判断是不是第一条记录
            if (tempConId == null) {
                tempConId = conId;
                tempDate = date;
                //把第一条记录当做一个诊室
                HashMap temp = new HashMap() {{
                    put("deptName", deptName);
                    put("conId", conId);
                    put("conName", conName);

                    /*
                     * 该诊室出诊计划
                     * 为了保证添加的顺序不被打乱，必须用LinkedHashMap，不可以是HashMap
                     */
                    put("plan", new LinkedHashMap() {{
                        put(date,doctorName);
                    }});
                }};
                tempResult.put(conId, temp);
            }
            //非第一条记录，但是该记录与前一条记录是相同诊室，但不是同一天出诊
            else if (tempConId == conId && !tempDate.equals(date)) {
                tempDate = date; //更新日期
                //取出该诊室
                HashMap map = (HashMap) tempResult.get(conId);
                //从诊室中取出出诊计划
                LinkedHashMap plan = (LinkedHashMap) map.get("plan");
                //创建新的出诊日期列表，添加该医生的名字
                plan.put(date,doctorName);
            }
            //如果该记录与上一条记录不是同诊室
            else if (tempConId != conId) {
                tempConId = conId;
                tempDate = date;
                //创建新的诊室对象
                HashMap temp = new HashMap() {{
                    put("deptName", deptName);
                    put("conId", conId);
                    put("conName", conName);

                    //出诊计划
                    put("plan", new LinkedHashMap() {{
                        //添加出诊列表
                        put(date,doctorName);
                    }});
                }};
                //把新诊室对象添加到结果集
                tempResult.put(conId, temp);
            }
        }


        //为了循环HashMap中的元素，所以提取所有的元素
        Set<Map.Entry> set = tempResult.entrySet();

        //循环每个元素
        set.forEach(one -> {
            //诊室对象
            HashMap map = (HashMap) one.getValue();
            //该诊室出诊计划
            //3天
            LinkedHashMap plan = (LinkedHashMap) map.get("plan");
            /*
             * 业务方法第二个参数，提取每个日期，判断出诊计划中是否有该日期。
             * 如果出诊计划中没有该日期，说明改天没有医生出诊
             */
            dateList.forEach(date -> {
                if (!plan.containsKey(date)) {
                    //某天没有医生出诊，就往出诊计划中添加空的名单列表
                    plan.put(date, "");
                }
            });

            //由于往LinkedHashMap中添加的新元素（空的出诊列表），所以要对所有元素排序
            TreeMap sort = MapUtil.sort(plan, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    String key1 = (String) o1;
                    String key2 = (String) o2;
                    boolean bool = new DateTime(key1).isAfter(new DateTime(key2));
                    return bool ? 1 : -1;
                }
            });
            //把排好序的出诊计划更新到诊室对象中
            map.replace("plan", sort);
        });

        //每个诊室的plan是TreeMap，我们要转换成列表形式，将来才能变成JSON数组
        Collection<HashMap> values = tempResult.values();

        values.forEach(one -> {

            //取出该诊室的出诊计划map集合
            TreeMap plan = (TreeMap) one.get("plan");

            //取出TreeMap每个元素
            Set<Map.Entry> tempSet = plan.entrySet();


            //把出诊计划保存到列表中
            tempSet.forEach(entry -> {
                one.put( entry.getKey(),entry.getValue());
            });
            one.remove("plan");
        });

        return values;
    }

    @Override
    public JSONArray searchWorkPlanInRange(WorkPlanInQueryDto workPlanInQueryDto, ArrayList dateList) {

        //1.查询出诊数据
        List<DoctorWorkPlanVo> doctorWorkPlanVos = doctorWorkPlanMapper.findDoctorWorkPlanVoByQueryDto(workPlanInQueryDto);

        Long tempSubId = null;
        String tempDate = null;
        HashMap tempResult = new HashMap();
        for (DoctorWorkPlanVo doctorWorkPlanVo : doctorWorkPlanVos) {

            String deptName = doctorWorkPlanVo.getDeptName();
            Long conId = doctorWorkPlanVo.getConId();
            String conName = doctorWorkPlanVo.getConName();
            String doctorName = doctorWorkPlanVo.getDoctorName();
            Long workPlanId = doctorWorkPlanVo.getWorkPlanId();
            String date = DateUtil.date(doctorWorkPlanVo.getDate()).toDateStr();
            

            //判断是不是第一条记录
            if (tempSubId == null) {
                tempSubId = conId;
                tempDate = date;
                //把第一条记录当做一个诊室
                HashMap temp = new HashMap() {{
                    put("deptName", deptName);
                    put("conId", conId);
                    put("conName", conName);
                    /*
                     * 该诊室出诊计划
                     * 为了保证添加的顺序不被打乱，必须用LinkedHashMap，不可以是HashMap
                     */
                    put("plan", new LinkedHashMap() {{
                        put(date, new ArrayList() {{
                            add(doctorName);
                        }});
                    }});
                }};
                tempResult.put(conId, temp);
            }
            //非第一条记录，但是该记录与前一条记录是相同诊室，而且是同一天出诊
            else if (tempSubId == conId && tempDate.equals(date)) {
                //取出该诊室
                HashMap map = (HashMap) tempResult.get(conId);
                //从诊室中取出出诊计划
                LinkedHashMap plan = (LinkedHashMap) map.get("plan");
                //找到该天出诊医生名单列表
                ArrayList doctors = (ArrayList) plan.get(date);
                //把医生名字添加到列表中
                doctors.add(doctorName);
            }
            //非第一条记录，但是该记录与前一条记录是相同诊室，但不是同一天出诊
            else if (tempSubId == conId && !tempDate.equals(date)) {
                tempDate = date; //更新日期
                //取出该诊室
                HashMap map = (HashMap) tempResult.get(conId);
                //从诊室中取出出诊计划
                LinkedHashMap plan = (LinkedHashMap) map.get("plan");
                //创建新的出诊日期列表，添加该医生的名字
                plan.put(date, new ArrayList() {{
                    add(doctorName);
                }});
            }
            //如果该记录与上一条记录不是同诊室
            else if (tempSubId != conId) {
                tempSubId = conId;
                tempDate = date;
                //创建新的诊室对象
                HashMap temp = new HashMap() {{
                    put("deptName", deptName);
                    put("conId", conId);
                    put("conName", conName);
                    //出诊计划
                    put("plan", new LinkedHashMap() {{
                        //添加出诊列表
                        put(date, new ArrayList() {{
                            add(doctorName);
                        }});
                    }});
                }};
                //把新诊室对象添加到结果集
                tempResult.put(conId, temp);
            }
        }

        //为了循环HashMap中的元素，所以提取所有的元素
        Set<Map.Entry> set = tempResult.entrySet();

        //循环每个元素
        set.forEach(one -> {
            //诊室对象
            HashMap map = (HashMap) one.getValue();
            //该诊室出诊计划
            LinkedHashMap plan = (LinkedHashMap) map.get("plan");
            /*
             * 业务方法第二个参数，提取每个日期，判断出诊计划中是否有该日期。
             * 如果出诊计划中没有该日期，说明改天没有医生出诊
             */
            dateList.forEach(date -> {
                if (!plan.containsKey(date)) {
                    //某天没有医生出诊，就往出诊计划中添加空的名单列表
                    plan.put(date, new ArrayList<>());
                }
            });

            //由于往LinkedHashMap中添加的新元素（空的出诊列表），所以要对所有元素排序
            TreeMap sort = MapUtil.sort(plan, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    String key1 = (String) o1;
                    String key2 = (String) o2;
                    boolean bool = new DateTime(key1).isAfter(new DateTime(key2));
                    return bool ? 1 : -1;
                }
            });
            //把排好序的出诊计划更新到诊室对象中
            map.replace("plan", sort);
        });

        //每个诊室的plan是TreeMap，我们要转换成列表形式，将来才能变成JSON数组
        Collection<HashMap> values = tempResult.values();
        values.forEach(one -> {
            TreeMap plan = (TreeMap) one.get("plan");
            //取出TreeMap每个元素
            Set<Map.Entry> tempSet = plan.entrySet();
            ArrayList temp = new ArrayList();
            //把出诊计划保存到列表中
            tempSet.forEach(entry -> {
                temp.add(new HashMap() {{
                    put("date", entry.getKey());
                    put("doctors", entry.getValue());
                }});
            });
            //更新诊室对象的plan
            one.replace("plan", temp);
        });
        return JSONUtil.parseArray(values);
    }

    @Override
    public Collection<WorkPlanScheduleVo> searchConSchedule(WorkPlanScheduleQueryDto workPlanScheduleQueryDto) {


        //1.查询数据
        List<WorkPlanScheduleVo> doctorWorkPlanScheduleByQueryDto = doctorWorkPlanMapper.findDoctorWorkPlanScheduleByQueryDto(workPlanScheduleQueryDto);


        Map<Long,WorkPlanScheduleVo> tempMap=new LinkedHashMap<>();

        Long doctorId=null;
        for(WorkPlanScheduleVo workPlanScheduleVo:doctorWorkPlanScheduleByQueryDto){

            //判断是否是第一条记录
            if(doctorId==null){
                doctorId=workPlanScheduleVo.getDoctorId();
                WorkPlanScheduleVo work=new WorkPlanScheduleVo();
                BeanUtils.copyProperties(workPlanScheduleVo,work);
                if(workPlanScheduleVo.getSlot()==1){
                    work.setForenoons("出诊");
                }
                if(workPlanScheduleVo.getSlot()==2){
                    work.setAfternoon("出诊");
                }
                tempMap.put(doctorId,work);
                //如果下一条与上一条id相同，则更新出诊信息
            }else if(doctorId.equals(workPlanScheduleVo.getDoctorId())){
                WorkPlanScheduleVo work = tempMap.get(doctorId);
                if(workPlanScheduleVo.getSlot()==1){
                    work.setForenoons("出诊");
                }
                if(workPlanScheduleVo.getSlot()==2){
                    work.setAfternoon("出诊");
                }
                //不是同一医生
            }else{
                doctorId=workPlanScheduleVo.getDoctorId();
                WorkPlanScheduleVo work=new WorkPlanScheduleVo();
                BeanUtils.copyProperties(workPlanScheduleVo,work);
                if(workPlanScheduleVo.getSlot()==1){
                    work.setForenoons("出诊");
                }
                if(workPlanScheduleVo.getSlot()==2){
                    work.setAfternoon("出诊");
                }
                tempMap.put(doctorId,work);
            }
        }

        //未出诊标记
        tempMap.values().stream().forEach(workPlanScheduleVo -> {
            if(StringUtils.isEmpty(workPlanScheduleVo.getForenoons())){
                workPlanScheduleVo.setForenoons("未出诊");
            }
            if(StringUtils.isEmpty(workPlanScheduleVo.getAfternoon())){
                workPlanScheduleVo.setAfternoon("未出诊");
            }
        });


        return tempMap.values();
    }

    @Override
    public List<Map> searchCanRegisterInDateRange(WorkPlanInQueryDto workPlanInQueryDto) {

        List<String> list = doctorWorkPlanMapper.searchCanRegisterInDateRange(workPlanInQueryDto);

        DateTime startDate = DateUtil.parse(workPlanInQueryDto.getStartDate());
        DateTime endDate = DateUtil.parse(workPlanInQueryDto.getEndDate());

        //18 19 20 21 22 23 24
        DateRange range = DateUtil.range(startDate, endDate, DateField.DAY_OF_MONTH);
        ArrayList result = new ArrayList();
        while (range.hasNext()) {
            String date = range.next().toDateStr();
            if (list.contains(date)) {
                result.add(new HashMap() {{
                    put("date", date);
                    put("status", "出诊");
                }});
            } else {
                result.add(new HashMap() {{
                    put("date", date);
                    put("status", "无号");

                }});
            }
        }
        return result;
    }

    @Override
    public List<WorkPlanDateVo> searchDeptSubDoctorPlanInDay(WorkPlanScheduleQueryDto workPlanScheduleQueryDto) {
        return doctorWorkPlanMapper.searchDeptSubDoctorPlanInDay(workPlanScheduleQueryDto);
    }

    @Override
    public List<DoctorScheduleVo> searchDoctorWorkPlanSchedule(DoctorScheduleQueryDto doctorScheduleQueryDto) {
        return doctorWorkPlanMapper.searchDoctorWorkPlanSchedule(doctorScheduleQueryDto);
    }

}
