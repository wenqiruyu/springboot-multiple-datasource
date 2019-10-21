package com.datasource.two.service.impl;

import com.datasource.two.dao.two.ITwoUserDao;
import com.datasource.two.entity.TwoUser;
import com.datasource.two.service.ITwoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：TwoUserServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
@Service
public class TwoUserServiceImpl implements ITwoUserService {

    @Autowired
    private ITwoUserDao twoUserDao;

    @Override
    public List<TwoUser> getAllUser() {
        return twoUserDao.getAllUser();
    }
}
