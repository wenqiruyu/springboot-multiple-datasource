package com.datasource.one.service.impl;

import com.datasource.one.dao.one.IOneUserDao;
import com.datasource.one.entity.OneUser;
import com.datasource.one.service.IOneUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：OneUserServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
@Service
public class OneUserServiceImpl implements IOneUserService {

    @Autowired
    private IOneUserDao oneUserDao;

    @Override
    public List<OneUser> getAllUser() {

        return oneUserDao.getAllUser();
    }
}
