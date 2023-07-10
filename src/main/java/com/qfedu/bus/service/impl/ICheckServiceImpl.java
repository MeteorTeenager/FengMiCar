package com.qfedu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.bus.domain.*;
import com.qfedu.bus.mapper.CarMapper;
import com.qfedu.bus.mapper.CheckMapper;
import com.qfedu.bus.mapper.CustomerMapper;
import com.qfedu.bus.mapper.RentMapper;
import com.qfedu.bus.service.ICheckService;
import com.qfedu.sys.domain.User;
import com.qfedu.sys.utils.DataGridView;
import com.qfedu.sys.utils.RandomUtils;
import com.qfedu.sys.utils.SysConstant;
import com.qfedu.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */
@Service
@Transactional
public class ICheckServiceImpl implements ICheckService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CarMapper carMapper;


    @Autowired
    private CheckMapper checkMapper;

    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //1.获取rent出租单
        Rent rent = rentMapper.selectRentById(rentid);

        //2.获取客户信息
        String identity = rent.getIdentity();
        Customer customer = customerMapper.findCustomerById(identity);

        //3.获取车辆信息
        String carnumber = rent.getCarnumber();
        Car car = carMapper.selectCarByCarNumer(carnumber);

        //4.获取检查单信息
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_JC));
        check.setRentid(rentid);
        User user  = (User)WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        check.setCheckdate(new Date());

        //5.将数据封装到map中返回
        Map<String,Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);
        return map;
    }

    @Override
    public void saveCheck(CheckVo checkVo) {
        //1.设置出租单 为已归还
        String rentid = checkVo.getRentid();
        RentVo rentVo = new RentVo();
        rentVo.setRentid(rentid);
        rentVo.setRentflag(SysConstant.RENT_BACK_TRUE);
        rentMapper.updateRent(rentVo);

        //2.设置车辆为未出租
        Rent rent = rentMapper.selectRentById(rentid);
        String carNumber = rent.getCarnumber();
        CarVo carVo = new CarVo();
        carVo.setCarnumber(carNumber);
        carVo.setIsrenting(SysConstant.RENT_CAR_FALSE);
        carMapper.updateCar(carVo);

        //3.添加检查单
        checkVo.setCreatetime(new Date());
        checkMapper.addCheck(checkVo);
    }

    @Override
    public DataGridView loadAllCheck(CheckVo checkVo) {
        Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }



    @Override
    public void updateCheck(CheckVo checkVo) {
        checkMapper.updateCheck(checkVo);
    }

    @Override
    public void deleteCheck(String checkId) {
        checkMapper.deleteCheckById(checkId);
    }


    @Override
    public void deleteBatchCheck(String[] ids) {
        if(ids != null && ids.length > 0 ){
            for (String id : ids) {
                deleteCheck(id);
            }
        }
    }
}
