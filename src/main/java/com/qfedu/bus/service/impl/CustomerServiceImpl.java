package com.qfedu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.bus.domain.Customer;
import com.qfedu.bus.domain.CustomerVo;
import com.qfedu.bus.mapper.CustomerMapper;
import com.qfedu.bus.service.ICustomerService;
import com.qfedu.sys.utils.DataGridView;
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
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public DataGridView queryCustomer(CustomerVo customerVo) {
        //分页查询客户信息
        Page<Object> page = PageHelper.startPage(customerVo.getPage(),customerVo.getLimit());
        //查询全部的客户信息(如果用户传递了过滤参数,也会进行筛选)
        List<Customer> data = customerMapper.queryAllCustomer(customerVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCustomer(CustomerVo customerVo) {
        //1.设置当前时间给customer的createtime
        customerVo.setCreatetime(new Date());
        //2.调用mapper的添加方法
        customerMapper.insertSelect(customerVo);
    }

    @Override
    public void deleteCustomer(String identity) {
        customerMapper.deleteCustomerById(identity);
    }

    @Override
    public void updateCustomer(CustomerVo customerVo) {
        customerMapper.updateCustomer(customerVo);
    }

    @Override
    public void deleteBatchCustomer(CustomerVo customerVo) {
        for (String id : customerVo.getIds()) {
            deleteCustomer(id);
        }
    }

    @Override
    public Customer findCustomerByIdentity(String identity) {

        return  customerMapper.findCustomerById(identity);
    }
}
