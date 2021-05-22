package com.cb.epidemic.service;

import com.cb.epidemic.bean.DailyEpidemicInfo;
import com.cb.epidemic.bean.EpidemicDetailInfo;
import com.cb.epidemic.bean.ProvinceInfo;

import java.util.List;

public interface EpidemicService {
    /**
     * 保存当日的疫情数据，返回还未录入数据的省份列表
     * @param dailyEpidemicInfo
     * @param userId
     * @return
     */
    List<ProvinceInfo> saveData(DailyEpidemicInfo dailyEpidemicInfo,Integer userId);

    /**
     * 获取最新的疫情数据
     * @return
     */
    List<EpidemicDetailInfo> findLastestData();
}
