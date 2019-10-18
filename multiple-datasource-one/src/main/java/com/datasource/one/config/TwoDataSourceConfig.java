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
@MapperScan(basePackages = "com.datasource.one.dao.two", sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class TwoDataSourceConfig {

    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource twoDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory twoSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:com/datasource/one/dao/two/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "twoTransactionManager")
    public DataSourceTransactionManager twoTransactionManager(@Qualifier("twoDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "twoSqlSessionTemplate")
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);
    }

}