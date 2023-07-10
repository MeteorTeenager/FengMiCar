package com.qfedu.bus.controller;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */

import com.qfedu.bus.domain.Customer;
import com.qfedu.bus.domain.Rent;
import com.qfedu.bus.domain.RentVo;
import com.qfedu.bus.service.ICustomerService;
import com.qfedu.bus.service.IRentService;
import com.qfedu.sys.domain.User;
import com.qfedu.sys.utils.*;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@RequestMapping("rent")
@Controller
public class RentController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IRentService rentService;

    //跳转到汽车出租页
    @RequestMapping("toRentCarManager")
    public String toRentCarManager(){
        return "business/rent/rentCarManager";
    }


    /**
     * 跳转到出租单管理页面
     */
    @RequestMapping("toRentManager")
    public String toRentManager(){
        return "business/rent/rentManager";
    }


    /**
     * 判断客户是否存在
     */
    @RequestMapping("checkCustomerExist")
    @ResponseBody
    public ResultObj checkCustomerExist(RentVo rentVo){
        Customer customer = customerService.findCustomerByIdentity(rentVo.getIdentity());
        if(customer != null){
            return ResultObj.STATUS_TRUE;
        }else{
            return ResultObj.STATUS_FALSE;
        }

    }


    /**
     * 初始化出租单
     */
    @RequestMapping("initRentFrom")
    @ResponseBody
    public RentVo initRentFrom(RentVo rentVo){
        //1.设置出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_CZ));

        //2.设置起租时间
        rentVo.setBegindate(new Date());

        //3.设置操作员
        HttpSession httpSession = WebUtils.getHttpSession();
        User user = (User) httpSession.getAttribute("user");
        rentVo.setOpername(user.getRealname());

        //4.最后返回封装好数据的RentVo对象
        return rentVo;
    }


    /**
     * 添加出租单
     */
    @RequestMapping("saveRent")
    @ResponseBody
    public ResultObj saveRent(RentVo rentVo){
        try {
            rentService.saveRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 分页查询出租单
     */
    @RequestMapping("loadAllRent")
    @ResponseBody
    public DataGridView  loadAllRent(RentVo rentVo){

        return rentService.loadAllRent(rentVo);
    }


    /**
     * 出租单修改
     */
    @RequestMapping("updateRent")
    @ResponseBody
    public  ResultObj  updateRent(RentVo rentVo){
        try {
            rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除出租单
     *
     */
    @RequestMapping("deleteRent")
    @ResponseBody
    public ResultObj deleteRent(RentVo rentVo){
        try {
            rentService.deleteRent(rentVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 导出出租单
     */
    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid){
        //1.根据rentid查询rent对象
        Rent rent = rentService.findRentById(rentid);
        //2.通过rent对象获取到identity , 获取到客户对象
        String identity =  rent.getIdentity();
        Customer customer = customerService.findCustomerByIdentity(identity);

        //3.设置 excel文件名称
        String customerName = customer.getCustname();
        String fileName = customerName+"-出租单信息.xls";
        //4.设置sheet 名称
        String sheetName = customerName+"的出租单信息";
        //5.调用工具类 ExportRentUtils 的导出方法
        ByteArrayOutputStream bos = ExportRentUtils.exportRent(rent, customer, sheetName);
        try {
            //6.设置编码 filename  excel文件名称设置
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //7.创建Header ,给浏览器看
            HttpHeaders header = new HttpHeaders();
            //8.设置mediaType   header
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //9.设置下载的文件名称   header
            header.setContentDispositionFormData("attachment",fileName);

            //10.创建ResponseEntity 将数据组装返回
            return new ResponseEntity<>(bos.toByteArray(),header, HttpStatus.CREATED);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
