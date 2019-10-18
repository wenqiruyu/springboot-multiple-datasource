package com.datasource.one.controller;

import com.datasource.one.entity.TwoUser;
import com.datasource.one.service.ITwoUserService;
import com.datasource.one.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：TwoUserController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
@RestController
@RequestMapping("/two")
public class TwoUserController {

    @Autowired
    private ITwoUserService twoUserService;

    @PostMapping("/getAllUser")
    public ServerResponse getAllUsr() {

        List<TwoUser> allUser = twoUserService.getAllUser();
        return ServerResponse.createBySuccess(allUser);
    }
}
