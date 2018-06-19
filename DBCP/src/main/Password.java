package main;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Password {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUsername("root");
        dataSource.setPassword("scp15335747148");
        dataSource.setUrl("jdbc:mysql://47.104.28.182:3306/lab");

        //getPassword
        String password = dataSource.getPassword();
        System.out.println("password: " + password);
        //setPassword

        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
