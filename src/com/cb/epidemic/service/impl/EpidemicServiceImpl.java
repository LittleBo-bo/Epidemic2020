package com.cb.epidemic.service.impl;

import com.cb.epidemic.bean.DailyEpidemicInfo;
import com.cb.epidemic.bean.EpidemicDetailInfo;
import com.cb.epidemic.bean.EpidemicInfo;
import com.cb.epidemic.bean.ProvinceInfo;
import com.cb.epidemic.mapper.EpidemicMapper;
import com.cb.epidemic.mapper.ProvinceMapper;
import com.cb.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EpidemicServiceImpl implements EpidemicService {
    @Autowired
    private EpidemicMapper epidemicMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    /**
     * 保存当日的疫情数据，返回还未录入数据的省份列表
     *
     * @param dailyEpidemicInfo
     * @param userId
     * @return
     */
    @Override
    public List<ProvinceInfo> saveData(DailyEpidemicInfo dailyEpidemicInfo, Integer userId) {
        //获取当前数据，与系统时间一致
        Date current = new Date();
        //数据的日期
        String[] ymd = dailyEpidemicInfo.getDate().split("-");//2020-10-30
        short year =0,month=0,day=0;
        year=Short.parseShort(ymd[0]);
        month=Short.parseShort(ymd[1]);
        day=Short.parseShort(ymd[2]);
        //遍历疫情信息
        for(EpidemicInfo epidemicInfo:dailyEpidemicInfo.getArray()){
            //设置录入该数据的用户编号
            epidemicInfo.setUserId(userId);
            //设置数据录入的日期
            epidemicInfo.setInputDate(current);
            //设置数据对应的日期
            epidemicInfo.setDataYear(year);
            epidemicInfo.setDataMonth(month);
            epidemicInfo.setDataDay(day);
            epidemicMapper.saveInfo(epidemicInfo);
        }
        return provinceMapper.findNoDataProvinces(year,month,day);
    }

    /**
     * 获取最新的疫情数据
     *
     * @return
     */
    @Override
    public List<EpidemicDetailInfo> findLastestData() {
        //查询每个省份的累计数量和当日新增数量
        Calendar calendar = new GregorianCalendar(); //获取当前日期
        short year =0,month=0,day=0;
        year=(short) calendar.get(Calendar.YEAR);//年
        month=(short)( calendar.get(Calendar.MONTH)+1);//月份（0-11）
        day=(short) calendar.get(Calendar.DATE);//日

        Map<String,Short> condition = new HashMap<>();
        condition.put("year",year);
        condition.put("month",month);
        condition.put("day",day);
        //查询每个省份的累计数量和当日新增量

        return epidemicMapper.findLastestData(condition);
    }
}
