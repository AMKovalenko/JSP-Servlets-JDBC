package model;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Александр on 23.09.2017.
 */
public class JDBCHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Statement statement = null;
    private Connection connection = null;

    private static JDBCHelper INSTANCE_JDBC;

    private JDBCHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized JDBCHelper getInstanceJdbc(){
        if (INSTANCE_JDBC == null){
            INSTANCE_JDBC =  new JDBCHelper();
        }
        return INSTANCE_JDBC;
    }

    public Statement getStatement() {
        try {
            statement = connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }
}
