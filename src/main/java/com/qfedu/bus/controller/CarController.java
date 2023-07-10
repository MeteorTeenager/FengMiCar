package com.qfedu.bus.controller;

import com.qfedu.bus.domain.CarVo;
import com.qfedu.bus.service.ICarService;
import com.qfedu.sys.utils.AppFileUtils;
import com.qfedu.sys.utils.DataGridView;
import com.qfedu.sys.utils.ResultObj;
import com.qfedu.sys.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
@Controller
@RequestMapping("car")
public class CarController {

    @Autowired
    private ICarService carService;

    /**
     * 跳转到车辆管理页面
     */
    @RequestMapping("toCarManager")
    public String toCarManager(){
        return "business/car/carManager";
    }

    /**
     * 分页查询
     * 参数: CarVo
     * 返回: DataGridView
     */
    @RequestMapping("loadAllCar")
    @ResponseBody
    public DataGridView loadAllCar(CarVo carVo){
        return carService.findAllCar(carVo);
    }

    /**
     * 添加车辆
     * 参数 CarVo
     * 返回: ResultObj
     *
     */

    @RequestMapping("addCar")
    @ResponseBody
    public ResultObj addCar(CarVo carVo){
        try {
            //1.设置时间
            carVo.setCreatetime(new Date());
            //2.判断如果图片不是默认推片,那就使我们上传的图片, 将图片的后缀删除掉
            if(!carVo.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)){
                //改名,去后缀
                String fileName = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(fileName);
            }
            //3.调用service的添加方法
            carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 删除车辆信息
     */
    @RequestMapping("deleteCar")
    @ResponseBody
    public ResultObj  deleteCar(String carnumber){
        try {
            carService.deleteCarByCarNumber(carnumber);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 更新车辆信息
     */
    @RequestMapping("updateCar")
    @ResponseBody
    public ResultObj updateCar(CarVo carVo){

        try {
            carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     */
    @RequestMapping("deleteBatchCar")
    @ResponseBody
    public ResultObj deleteBatchCar(CarVo carVo){
        try {
            carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }
}
