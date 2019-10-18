package com.datasource.one.dao.one;

import com.datasource.one.entity.OneUser;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：IOneUserDao
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
public interface IOneUserDao {

    List<OneUser> getAllUser();
}
