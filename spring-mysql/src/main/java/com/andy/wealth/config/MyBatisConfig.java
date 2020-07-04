package com.andy.wealth.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan("com.andy.wealth")
@EnableTransactionManagement
public class MyBatisConfig {

    @Bean
    @Primary
    public DataSource druidDataSource(DBProperties dbProperties) {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(dbProperties.getUrl());
        datasource.setUsername(dbProperties.getUsername());
        datasource.setPassword(dbProperties.getPassword());
        datasource.setDriverClassName(dbProperties.getDriverClassName());
        datasource.setInitialSize(dbProperties.getInitialSize());
        datasource.setMinIdle(dbProperties.getMinIdle());
        datasource.setMaxActive(dbProperties.getMaxActive());
        datasource.setMaxWait(dbProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dbProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dbProperties.getValidationQuery());
        datasource.setTestWhileIdle(dbProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(dbProperties.isTestOnBorrow());
        datasource.setTestOnReturn(dbProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(dbProperties.isPoolPreparedStatements());
        try {
            datasource.setFilters(dbProperties.getFilters());
        } catch (SQLException e) {
            //logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
