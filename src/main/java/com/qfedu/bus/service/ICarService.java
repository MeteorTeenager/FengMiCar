package com.qfedu.bus.service;

import com.qfedu.bus.domain.CarVo;
import com.qfedu.sys.utils.DataGridView;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface ICarService {
    //分页查询car信息
    public DataGridView findAllCar(CarVo carVo);

    void addCar(CarVo carVo);

    void deleteCarByCarNumber(String carNumber);

    void updateCar(CarVo carVo);

    void deleteBatchCar(String[] ids);

}
