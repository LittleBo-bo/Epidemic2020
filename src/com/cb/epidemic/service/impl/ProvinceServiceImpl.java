package com.cb.epidemic.service.impl;

import com.cb.epidemic.bean.ProvinceInfo;
import com.cb.epidemic.mapper.ProvinceMapper;
import com.cb.epidemic.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceServiceImpl implements ProvinceService {
    //实例化
    @Autowired
    private ProvinceMapper provinceMapper;
    /**
     * 获取还未录入数据的省份的列表
     * @param date
     * @return
     */
    @Override
    public List<ProvinceInfo> findNoDataProvinces(String date){
        short year=0,month=0,day=0;
        String[] array = date.split("-");
        List<ProvinceInfo> list = null;
        if(array.length>=3){
            year = Short.parseShort(array[0]);
            month = Short.parseShort(array[1]);
            day = Short.parseShort(array[2]);
            list = provinceMapper.findNoDataProvinces(year,month,day);
        }
        return list;
    }
}
