package com.qfedu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.bus.domain.Car;
import com.qfedu.bus.domain.CarVo;
import com.qfedu.bus.mapper.CarMapper;
import com.qfedu.bus.service.ICarService;
import com.qfedu.sys.utils.AppFileUtils;
import com.qfedu.sys.utils.DataGridView;
import com.qfedu.sys.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
@Service
@Transactional
public class ICarServiceImpl implements ICarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * 分页条件查询车辆
     * @param carVo
     * @return
     */
    @Override
    public DataGridView findAllCar(CarVo carVo) {
        //1.分页查询 PageHelper
        Page<Object> page = PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        //2.查全部操作
        List<Car> data = carMapper.queryAllCar(carVo);
        //3.返回结果DataGridView
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCar(CarVo carVo) {
        carMapper.addCar(carVo);
    }

    /**
     * 删除思路：
     * 1.要删除图片
     * 2.删除该车辆信息
     * @param carNumber
     */
    @Override
    public void deleteCarByCarNumber(String carNumber) {
        //1.通过carNumber查找该车辆对象
        Car car = carMapper.selectCarByCarNumer(carNumber);
        //2.获取该车辆的 carimg 图片地址
        String carimgPath = car.getCarimg();
        //3.删除该图片 ， 判断图片是否为默认图片，如果不是默认图片就删除
        if(!carimgPath.equals(SysConstant.DEFAULT_CAR_IMG)){
            AppFileUtils.deleteFileUsePath(carimgPath);
        }

        //4.删除车辆信息，根据id进行删除
        carMapper.deleteCarByCarNum(carNumber);
    }

    //更新方法
    @Override
    public void updateCar(CarVo carVo) {
        //1.我们修改图片: 如果用户修改的是否图片上传,新图片会上传到  D:upload/2023-07-03/32253w5353.jpg_temp
        String newCarimgTemp = carVo.getCarimg();

        //2.如果确定用户确实是上传了,需要就该他们名称,并且把_temp去掉
        if(newCarimgTemp.endsWith(SysConstant.FILE_UPLOAD_TEMP)){
            //用户确实做了图片修改操作，需要就该他们名称,并且把_temp去掉
            String newCarimg = AppFileUtils.updateFileName(newCarimgTemp, SysConstant.FILE_UPLOAD_TEMP);  //2023-07-03/32253w5353.jpg
            //3.将新图片相对路径保存在carVo中, 目的是为了更新数据库中carimg字段
            carVo.setCarimg(newCarimg);
            //4.老图片需要删除
            String carnumber = carVo.getCarnumber();
            Car car = carMapper.selectCarByCarNumer(carnumber); //从数据库中查出来的
            String oldCarImg = car.getCarimg();
            AppFileUtils.deleteFileUsePath(oldCarImg);
        }

        //5.更新car信息
        carMapper.updateCar(carVo);
    }


    @Override
    public void deleteBatchCar(String[] ids) {
        if(ids == null || ids.length == 0 ){
            return;
        }
        for (String id : ids) {
            deleteCarByCarNumber(id);
        }
    }
}
