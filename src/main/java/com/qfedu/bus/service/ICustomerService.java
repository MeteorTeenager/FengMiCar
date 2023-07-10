package com.qfedu.bus.service;

import com.qfedu.bus.domain.Customer;
import com.qfedu.bus.domain.CustomerVo;
import com.qfedu.sys.utils.DataGridView;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface ICustomerService {
    public DataGridView queryCustomer(CustomerVo customerVo);

    void addCustomer(CustomerVo customerVo);

    void deleteCustomer(String identity);

    void updateCustomer(CustomerVo customerVo);

    void deleteBatchCustomer(CustomerVo customerVo);

    Customer findCustomerByIdentity(String identity);
}
