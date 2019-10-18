package com.datasource.one.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：OneDataSourceConfig
 * 类描述：数据源配置类
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
@Configuration
@MapperScan(basePackages = "com.datasource.one.dao.one", sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class OneDataSourceConfig {

    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource oneDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oneSqlSessionFactory")
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:com/datasource/one/dao/one/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "oneTransactionManager")
    public DataSourceTransactionManager oneTransactionManager(@Qualifier("oneDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oneSqlSessionTemplate")
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);
    }

}