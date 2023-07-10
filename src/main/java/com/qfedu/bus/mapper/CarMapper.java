package com.qfedu.bus.mapper;

import com.qfedu.bus.domain.Car;
import com.qfedu.bus.domain.CarVo;

import java.util.List;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface CarMapper {
    List<Car> queryAllCar(CarVo carVo);

    void addCar(CarVo carVo);

    Car selectCarByCarNumer(String carNumber);

    void deleteCarByCarNum(String carNumber);

    void updateCar(CarVo carVo);
}
