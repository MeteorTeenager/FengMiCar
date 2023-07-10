package com.qfedu.sys.controller;

import com.qfedu.sys.service.AccountService;
import com.qfedu.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */

@Controller
@RequestMapping("account")
public class AccountController {

    /**
     * localhost:8081/rental_A/account/transfer.action?inName=rose&outName=tom&money=100  ==> 以json的方式返回
     * @return
     */


    @Autowired
    private AccountService accountService;

    @RequestMapping("transfer")
    @ResponseBody  //将结果ResultObj对象以json的方式返回给前台
    public ResultObj transfer(String inName, String outName, double money){
        int flag = accountService.updateAccount(inName, outName, money);
        if(flag > 0){
            return ResultObj.UPDATE_SUCCESS;
        }else {
            return ResultObj.UPDATE_ERROR;
        }
    }
}
