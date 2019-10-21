package com.datasource.two.config;

import com.datasource.two.interceptor.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：DynamicDataSource
 * 类描述：动态数据源
 * 创建人：yingx
 * 创建时间： 2019/10/21
 * 修改人：yingx
 * 修改时间： 2019/10/21
 * 修改备注：
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        return DataSourceContextHolder.getDataSource();
    }
}
