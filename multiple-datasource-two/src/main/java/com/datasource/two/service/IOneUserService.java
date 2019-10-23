package com.datasource.two.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datasource.two.entity.OneUser;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：IOneUserService
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
public interface IOneUserService {

    List<OneUser> getAllUser();

    IPage<OneUser> getPageUserData(OneUser user, int page, int pageSize);
}
