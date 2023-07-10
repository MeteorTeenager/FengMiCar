package com.qfedu.bus.controller;

import com.qfedu.bus.domain.CustomerVo;
import com.qfedu.bus.service.ICustomerService;
import com.qfedu.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qfedu.sys.utils.DataGridView;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * 点击左侧菜单栏的 客户管理,右侧能够展示页面
     * ../customer/toCustomerManager.action
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 分页条件查询
     * 参数 CustomerVo  --- 封装一些查询条件 和分页的参数
     * 返回:  DataGridView
     */
    @RequestMapping("loadAllCustomer")
    @ResponseBody
    public DataGridView loadAllCustomer(CustomerVo customerVo){
        DataGridView dataGridView = customerService.queryCustomer(customerVo);
        return dataGridView;
    }


    /**
     * 添加客户
     */
    @RequestMapping("addCustomer")
    @ResponseBody
    public ResultObj addCustomer(CustomerVo customerVo){
        try {
            customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除客户
     */
    @RequestMapping("deleteCustomer")
    @ResponseBody
    public ResultObj deleteCustomer(String identity){
        try {
            customerService.deleteCustomer(identity);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更新客户信息
     */
    @RequestMapping("updateCustomer")
    @ResponseBody
    public ResultObj updateCustomer(CustomerVo customerVo){
        try {
            customerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteCustomer")
    @ResponseBody
    public ResultObj batchDeleteCustomer(CustomerVo customerVo){
        try {
            customerService.deleteBatchCustomer(customerVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
