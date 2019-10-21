package com.datasource.two.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.datasource.two.enums.DataSourceEmum;
import com.datasource.two.interceptor.CustomMetaObjectHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mockito.internal.configuration.GlobalConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：MybatisPlusConfig
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/21
 * 修改人：yingx
 * 修改时间： 2019/10/21
 * 修改备注：
 */
@Configuration
@MapperScan("com.datasource.two.dao")
public class MybatisPlusConfig {

    @Autowired
    private CustomMetaObjectHandler customMetaObjectHandler;

    /**
     * mybatis分页插件
     *
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author yingx
     * @date 2019/10/21
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    @Bean("datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource dataSource2() {

        return DataSourceBuilder.create().build();
    }

    @Bean("datasource2")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource dataSource1() {

        return DataSourceBuilder.create().build();
    }

    /**
     * 配置动态数据源
     *
     * @param dataSource1
     * @param dataSource2
     * @return javax.sql.DataSource
     * @author yingx
     * @date 2019/10/21
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("datasource1") DataSource dataSource1,
                                         @Qualifier("datasource1") DataSource dataSource2) {

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEmum.datasource1.getValue(), dataSource1);
        targetDataSources.put(DataSourceEmum.datasource2.getValue(), dataSource2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSource1); // 设置默认、主数据源
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(dataSource1(), dataSource2()));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:com/datasource/two/dao/**/*.xml"));
        //PerformanceInterceptor(),OptimisticLockerInterceptor()
        //添加分页功能
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfig());
        return sqlSessionFactory.getObject();
    }

    /**
     * mybatis全局配置，可在yml中配置
     *
     * @param
     * @return org.mockito.internal.configuration.GlobalConfiguration
     * @author yingx
     * @date 2019/10/21
     */
    @Bean
    public GlobalConfig globalConfig() {

        // 配置主键类型 AUTO:"数据库ID自增", INPUT:"用户输入ID",ID_WORKER:"全局唯一ID (数字类型唯一ID)", UUID:"全局唯一ID UUID";
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setDbType(DbType.MYSQL);
        dbConfig.setIdType(IdType.AUTO);
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDbConfig(dbConfig);
        //刷新配置
//		globalConfig.setRefresh(true);
        globalConfig.setMetaObjectHandler(customMetaObjectHandler);

        /*GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;*/
        return globalConfig;
    }
}
