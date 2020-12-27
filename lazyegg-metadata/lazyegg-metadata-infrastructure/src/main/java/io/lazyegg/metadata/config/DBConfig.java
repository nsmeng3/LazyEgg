package io.lazyegg.metadata.config;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import io.lazyegg.metadata.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DBConfig {
    public final static String DBConfig = "DBConfig";

    private static String driverClassName = "";
    @Resource
    private MySqlTablesMapper mySqlTablesMapper;
    @Resource
    private MySqlColumnsMapper mySqlColumnsMapper;
    @Resource
    private SqlServerTablesMapper sqlServerTablesMapper;
    @Resource
    private SqlServerColumnsMapper sqlServerColumnsMapper;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        Map<String, Object> param = getStringObjectMap(dataSource);
        // HikariPool
        String hikariPoolDriverClassName = "driverClassName";
        // DruidPool
        String druidPoolDriverClass = "driverClass";
        if (param.containsKey(hikariPoolDriverClassName)) {
            driverClassName = String.valueOf(param.get(hikariPoolDriverClassName));
        } else if (param.containsKey(druidPoolDriverClass)) {
            driverClassName = String.valueOf(param.get(druidPoolDriverClass));
        } else {
            throw new BizException("不支持当前数据库连接池");
        }

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage());
        }
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(reader);
        org.apache.ibatis.session.Configuration configuration = build.getConfiguration();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    @Bean
    @Primary
    public BaseTablesMapper getMetadataTableMapper() {
        switch (driverClassName) {
            case "com.microsoft.sqlserver.jdbc.SQLServerDriver":
                return sqlServerTablesMapper;
            case "com.mysql.cj.jdbc.Driver":
                return mySqlTablesMapper;
            default:
                throw new SysException("当前数据库暂不支持");
        }
    }

    @Bean
    @Primary
    public BaseColumnsMapper getMetadataColumnMapper() {
        switch (driverClassName) {
            case "com.microsoft.sqlserver.jdbc.SQLServerDriver":
                return sqlServerColumnsMapper;
            case "com.mysql.cj.jdbc.Driver":
                return mySqlColumnsMapper;
            default:
                throw new SysException("不支持当前数据库");
        }
    }

    private Map<String, Object> getStringObjectMap(DataSource dataSource) {
        Class c = dataSource.getClass();
        String name = c.getName();
        log.debug(name);
        Class className = null;
        try {
            className = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Object> param = new HashMap<>();
        try {
            //获取本身和父级对象
            for (; className != Object.class; className = className.getSuperclass()) {
                //获取所有私有字段
                Assert.notNull(className);
                Field[] fields = className.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    param.put(field.getName(), field.get(dataSource) == null ? "" : field.get(dataSource));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }


}
