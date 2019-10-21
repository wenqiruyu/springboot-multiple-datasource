package com.datasource.two.interceptor;

import com.datasource.two.enums.DataSourceEmum;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：DataSourceContextHolder
 * 类描述：获取、设置数据源类
 * 创建人：yingx
 * 创建时间： 2019/10/21
 * 修改人：yingx
 * 修改时间： 2019/10/21
 * 修改备注：
 */
public class DataSourceContextHolder {

    private static final ThreadLocal threadLocal = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dataSource
     * @return void
     * @author yingx
     * @date 2019/10/21
     */
    public static void setDataSource(DataSourceEmum dataSource) {

        threadLocal.set(dataSource.getValue());
    }

    /**
     * 获取当前数据源
     *
     * @param
     * @return java.lang.String
     * @author yingx
     * @date 2019/10/21
     */
    public static String getDataSource() {

        return (String) threadLocal.get();
    }

    /**
     * 清除上下文数据
     * @author yingx
     * @date 2019/10/21
     * @param
     * @return void
     */
    public static void clearDataSource() {

        threadLocal.remove();
    }
}
