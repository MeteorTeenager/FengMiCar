package com.qfedu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.bus.domain.CarVo;
import com.qfedu.bus.domain.Rent;
import com.qfedu.bus.domain.RentVo;
import com.qfedu.bus.mapper.CarMapper;
import com.qfedu.bus.mapper.RentMapper;
import com.qfedu.bus.service.IRentService;
import com.qfedu.sys.utils.DataGridView;
import com.qfedu.sys.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
@Service
@Transactional
public class RentServiceImpl implements IRentService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public void saveRent(RentVo rentVo) {
        //1.针对于car 是否出租的状态进行设定
        String carNumber = rentVo.getCarnumber();
        CarVo  carVo = new CarVo();
        carVo.setCarnumber(carNumber);
        carVo.setIsrenting(SysConstant.RENT_CAR_TRUE);  //将car的状态设置为已出租
        carMapper.updateCar(carVo);

        //2.针对于RentVO中没有设置的参数进行补充:  createTIme     rentflag
        rentVo.setRentflag(SysConstant.RENT_BACK_FALSE);  //未归还
        rentVo.setCreatetime(new Date());
        rentMapper.addRent(rentVo);
    }

    @Override
    public DataGridView loadAllRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        List<Rent> data = rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateRent(RentVo rentVo) {
        rentMapper.updateRent(rentVo);
    }

    @Override
    public void deleteRent(RentVo rentVo) {
        //1.找到车辆信息, 修改isrenting 的状态
        String rentId = rentVo.getRentid();
        Rent rent = rentMapper.selectRentById(rentId);

        CarVo carVo = new CarVo();
        String carnumber = rent.getCarnumber();
        carVo.setCarnumber(carnumber);
        carVo.setIsrenting(SysConstant.RENT_CAR_FALSE);
        carMapper.updateCar(carVo);

        //2.根据rentid删除出租单
        rentMapper.deleteRentById(rentId);
    }

    @Override
    public Rent findRentById(String rentid) {
        return rentMapper.selectRentById(rentid);
    }
}
