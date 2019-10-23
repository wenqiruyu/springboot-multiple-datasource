package com.datasource.two.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.datasource.two.entity.OneUser;
import com.datasource.two.service.IOneUserService;
import com.datasource.two.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：OneUserController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
@RestController
@RequestMapping("/one")
public class OneUserController {

    @Autowired
    private IOneUserService oneUserService;

    @PostMapping("/getAllUser")
    public ServerResponse getAllUsr() {

        List<OneUser> allUser = oneUserService.getAllUser();
        return ServerResponse.createBySuccess(allUser);
    }

    @GetMapping("/getAllUser/{page}/{pageSize}")
    public ServerResponse findAllUser(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {

        IPage<OneUser> pageUserData = oneUserService.getPageUserData(new OneUser(), page, pageSize);
        return ServerResponse.createBySuccess(pageUserData.getRecords());
    }
}
