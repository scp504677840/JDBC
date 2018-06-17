package main;

import utils.DBUtils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Driver：获取数据库连接。
 * MYSQL JDBC JAR 下载地址：https://dev.mysql.com/downloads/connector/j/
 */
public class Main {
    public static void main(String[] args) {

        //写法一
        //fun1();

        //写法二
        //fun2();

        //写法三【推荐】
        fun3();

    }

    private static void fun3() {
        Connection conn = DBUtils.conn();
        System.out.println(conn);
    }

    private static void fun2() {
        Connection conn = DBUtils.conn("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/lab", "root", "123456");
        System.out.println(conn);
    }

    private static void fun1() {
        try {
            //1.准备数据库驱动
            Driver driver = new com.mysql.jdbc.Driver();

            //2.准备数据库连接信息
            String url = "jdbc:mysql://localhost:3306/lab";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "123456");

            //3.连接数据库
            Connection connect = driver.connect(url, info);
            System.out.println(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
