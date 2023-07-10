package com.qfedu.bus.controller;

import com.qfedu.bus.domain.CheckVo;
import com.qfedu.bus.domain.Rent;
import com.qfedu.bus.service.ICheckService;
import com.qfedu.bus.service.IRentService;
import com.qfedu.sys.utils.DataGridView;
import com.qfedu.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */
@Controller
@RequestMapping("check")
public class CheckController {

    @Autowired
    private IRentService rentService;

    @Autowired
    private ICheckService checkService;

    /**
     * 页面跳转到汽车入库页面
     */
    @RequestMapping("toCheckCarManager")
    public String toCheckCarManager(){
        return "business/check/checkCarManager";
    }


    /**
     * 页面跳转到检查单页面
     */
    @RequestMapping("toCheckManager")
    public String toCheckManager(){
        return "business/check/checkManager";
    }


    /**
     * 判断出租单是否存在
     */
    @RequestMapping("checkRentExist")
    @ResponseBody
    public Rent checkRentExist(String rentid){
        //可以根据RentService 的id查询得到一个rent
        return rentService.findRentById(rentid);
    }

    /**
     * 初始化出租单
     */
    @RequestMapping("initCheckFormData")
    @ResponseBody
    public Map<String,Object> initCheckFormData(String rentid){
        return checkService.initCheckFormData(rentid);

    }

    /**
     * 保存检查单
     * 汽车入库 (还车)
     */
    @RequestMapping("saveCheck")
    @ResponseBody
    public ResultObj saveCheck(CheckVo checkVo){
        try {
            checkService.saveCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 分页查询出租单
     */
    @RequestMapping("loadAllCheck")
    @ResponseBody
    public DataGridView loadAllCheck(CheckVo checkVo){

        return checkService.loadAllCheck(checkVo);
    }


    /**
     * 编辑检查单
     */
    @RequestMapping("updateCheck")
    @ResponseBody
    public  ResultObj  updateCheck(CheckVo checkVo){
        try {
            checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除检查单
     */
    @RequestMapping("deleteCheck")
    @ResponseBody
    public ResultObj deleteCheck(CheckVo checkVo){
        try {
            checkService.deleteCheck(checkVo.getCheckid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除检查单
     */
    @RequestMapping("deleteBatchCheck")
    @ResponseBody
    public ResultObj deleteBatchCheck(CheckVo checkVo){
        try {
            checkService.deleteBatchCheck(checkVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
