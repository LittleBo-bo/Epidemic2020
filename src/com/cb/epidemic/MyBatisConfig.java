package com.cb.epidemic;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 对MyBatis的配置，等同于MyBatis.xml+spring-bean.xml
 */
//注解标签
    @Configuration
    @MapperScan(basePackages = "com.cb.epidemic.mapper")
public class MyBatisConfig {
    /**
     * 配置数据源
     */
   @Bean(name="dataSource",destroyMethod = "close")
   /**
    * 连接数据库
    */
    public  BasicDataSource basicDataSource(){
       BasicDataSource dataSource = new BasicDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver"); //驱动
       dataSource.setUrl("jdbc:mysql://localhost:3306/epidemic");//数据库地址
       dataSource.setUsername("root");//用户名
       dataSource.setPassword("990811");//密码

       dataSource.setInitialSize(3); //连接池的大小
       dataSource.setMaxActive(50);//最大活跃数
       dataSource.setMaxIdle(1);//最大空闲数
       dataSource.setMaxWait(4000);//等待连接时间
       dataSource.setDefaultAutoCommit(false);//禁止自动提交事务
       return dataSource;
   }
   //把函数交给spring管理
   @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
       SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
       //设置数据源
       factoryBean.setDataSource(dataSource);
       SqlSessionFactory factory = null;
       //设置xml文件中的类所在的包
       factoryBean.setTypeAliasesPackage("com.cb.epidemic.bean");
       //为了让mybatis自动将下划线分割的列名转化为小驼峰表示的属性名
       org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
       configuration.setMapUnderscoreToCamelCase(true);//举例 use_id  useId
       factoryBean.setConfiguration(configuration);

       //设置映射xml文件的路径
       try {
           Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/cb/epidemic/mapper/*Mapper.xml");
           factoryBean.setMapperLocations(resources);
           //转换类型 将实现类转换成object
           factory = factoryBean.getObject();

       } catch (Exception e) {
           e.printStackTrace();
       }
       return  factory;
   }

}
