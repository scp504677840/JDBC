package main;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://192.168.0.105:3306/lab");

        //setDriver
        try {
            dataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //setDriverClassName
        //getDriverClassName
        //setDriverClassLoader
        //getDriverClassLoader
        //getDriver

        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
