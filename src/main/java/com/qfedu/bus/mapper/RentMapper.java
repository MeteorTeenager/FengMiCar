package com.qfedu.bus.mapper;

import com.qfedu.bus.domain.Rent;
import com.qfedu.bus.domain.RentVo;

import java.util.List;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface RentMapper {
    void addRent(RentVo rentVo);

    List<Rent> queryAllRent(RentVo rentVo);

    void updateRent(RentVo rentVo);

    Rent selectRentById(String rentId);

    void deleteRentById(String rentId);
}
