package com.tungshine.erp.config;//package com.tungshine.erp.business.config;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * @ Author: TungShine
// * @ Description:
// * @ Date: Create in 17:45 2018/5/3
// * @ Modified By:
// */
//@Configuration
//@MapperScan("com.tungshine.erp.business.mapper")
//public class SessionFactoryConfig {
//
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public DataSource getDataSource() throws Exception {
//        Properties props = new Properties();
//        props.put("driverClassName", environment.getProperty("jdbc.driverClassName"));
//        props.put("url", environment.getProperty("jdbc.url"));
//        props.put("username", environment.getProperty("jdbc.username"));
//        props.put("password", environment.getProperty("jdbc.password"));
//        return DruidDataSourceFactory.createDataSource(props);
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        return factoryBean.getObject();
//    }
//}
