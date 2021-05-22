package com.cb.epidemic.service;

import com.cb.epidemic.bean.ProvinceInfo;

import java.util.List;

/**
 * 访问省份得到service
 */
public interface ProvinceService {
    /**
     * 获取还未录入信息的省份的列表
     * @param date
     * @return
     */
    List<ProvinceInfo> findNoDataProvinces(String date);
}
