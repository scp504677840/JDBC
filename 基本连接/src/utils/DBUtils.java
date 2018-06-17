package utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private DBUtils() {
    }

    public static Connection conn() {
        try {
            Properties properties = new Properties();
            InputStream in = DBUtils.class.getResourceAsStream("jdbc.properties");
            properties.load(in);

            String driverClassName = properties.getProperty("driver");
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Properties info = new Properties();
            info.setProperty("user", user);
            info.setProperty("password", password);

            Driver driver = (Driver) Class.forName(driverClassName).getConstructor().newInstance();
            return driver.connect(jdbcUrl, info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection conn(String driverClass, String url, String user, String password) {
        try {
            Driver driver = (Driver) Class.forName(driverClass).getConstructor().newInstance();
            Properties info = new Properties();
            info.put("user", user);
            info.put("password", password);
            return driver.connect(url, info);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
