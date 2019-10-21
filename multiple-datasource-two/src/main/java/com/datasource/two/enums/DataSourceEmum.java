package com.datasource.two.enums;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：DataSourceEmum
 * 类描述：数据源枚举类
 * 创建人：yingx
 * 创建时间： 2019/10/21
 * 修改人：yingx
 * 修改时间： 2019/10/21
 * 修改备注：
 */
public enum DataSourceEmum {

    datasource1("datasource1"), datasource2("datasource2");

    private String value;

    DataSourceEmum(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }
}
