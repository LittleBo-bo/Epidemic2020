package com.cb.epidemic.controller;

import com.cb.epidemic.bean.*;
import com.cb.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/epidemicData")
public class EpidemicController {
    @Autowired
    private EpidemicService epidemicService;

    /**
     * 录入疫情数据
     * @param dailyEpidemicInfo
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/ajax/input")
    @ResponseBody
    public AjaxResponseInfo inputData(@RequestBody DailyEpidemicInfo dailyEpidemicInfo , Model model, HttpSession session) {
            AjaxResponseInfo responseInfo = new AjaxResponseInfo();
            //从session中获取当前登录系统的用户信息
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user==null){
            responseInfo.setCode(-2); //登录失败  0代表登录成功 ，小于零代表登录失败
            responseInfo.setMsg("你还没有登录");
        }else {
            //将数据保存
            List<ProvinceInfo> list = epidemicService.saveData(dailyEpidemicInfo,user.getUserId());
            responseInfo.setData(list);
        }

          return responseInfo;
        }

    /**
     * 查询最新疫情信息
     * @return
     */
    @GetMapping("/ajax/lastestData")
    @ResponseBody
    public  AjaxResponseInfo findLastestData(){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<EpidemicDetailInfo> list = epidemicService.findLastestData();
        responseInfo.setData(list);
        return  responseInfo;


        }
}
