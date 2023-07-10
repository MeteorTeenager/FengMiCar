package com.qfedu.bus.mapper;

import com.qfedu.bus.domain.Customer;
import com.qfedu.bus.domain.CustomerVo;

import java.util.List;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface CustomerMapper {

    List<Customer> queryAllCustomer(CustomerVo customerVo);

    void insertSelect(CustomerVo customerVo);

    void deleteCustomerById(String identity);

    void updateCustomer(CustomerVo customerVo);

    Customer findCustomerById(String identity);
}
