package com.qfedu.bus.service;

import com.qfedu.bus.domain.Rent;
import com.qfedu.bus.domain.RentVo;
import com.qfedu.sys.utils.DataGridView;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface IRentService {

    void saveRent(RentVo rentVo);

    DataGridView loadAllRent(RentVo rentVo);

    void updateRent(RentVo rentVo);

    void deleteRent(RentVo rentVo);

    Rent findRentById(String rentid);
}
