package com.datasource.two.aspect;

import com.datasource.two.enums.DataSourceEmum;
import com.datasource.two.interceptor.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：DataSourceSwitchAspect
 * 类描述：数据源切换
 * 创建人：yingx
 * 创建时间： 2019/10/21
 * 修改人：yingx
 * 修改时间： 2019/10/21
 * 修改备注：
 */
@Component
@Order(value = -100)
@Aspect
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.datasource.two.dao.one..*.*(..))")
    private void datasource1Aspect() {
    }

    @Pointcut("execution(* com.datasource.two.dao.two..*.*(..))")
    private void datasource2Aspect() {
    }

    @Before("datasource1Aspect()")
    public void datasource1() {

        DataSourceContextHolder.setDataSource(DataSourceEmum.datasource1);
    }

    @Before("datasource2Aspect()")
    public void datasource2() {

        DataSourceContextHolder.setDataSource(DataSourceEmum.datasource2);
    }
}