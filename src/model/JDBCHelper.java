package model;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Александр on 23.09.2017.
 */
public class JDBCHelper{
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection = null;

    private final HikariDataSource connectionPool;

    private static JDBCHelper INSTANCE_JDBC;

    private JDBCHelper() {
            Properties props = new Properties();
            props.setProperty("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            props.setProperty("dataSource.url", URL);
            props.setProperty("dataSource.user", USERNAME);
            props.setProperty("dataSource.password", PASSWORD);
            props.setProperty("poolName", "ConnectionPool");
            props.setProperty("maximumPoolSize", "10"); // в этом пуле будет максимум 5 соединений
            props.setProperty("minimumIdle", "1"); // как минимум одно активное соединение там будет жить постоянно
            connectionPool = new HikariDataSource(new HikariConfig(props));
    }

    public static synchronized JDBCHelper getInstanceJdbc(){
        if (INSTANCE_JDBC == null){
            INSTANCE_JDBC =  new JDBCHelper();
        }
        return INSTANCE_JDBC;
    }

    public Connection getConnection() {
        try {
            connection = connectionPool.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

}
