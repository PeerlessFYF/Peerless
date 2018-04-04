package org.peerless.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * MySQL DB Factory
 * 
 * @author yinfeng.fyf
 */
public class MySqlDBFactory {

    private static DruidDataSource dataSource;

    static {
        try {
            dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/peerless?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setInitialSize(5);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(10);
            dataSource.setFilters("stat");
            dataSource.setPoolPreparedStatements(false);
            dataSource.setValidationQuery("show status like '%Service_Status%';");
            dataSource.setTestWhileIdle(true);
        } catch (SQLException e) {
        }
    }

    public static synchronized Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
        }
        return conn;
    }
    
}
