package com.datasource.two.service;

import com.datasource.two.entity.TwoUser;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：ITwoUserService
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
public interface ITwoUserService {

    List<TwoUser> getAllUser();
}
