package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        //演示增删改
        //fun1();

        //演示查询
        fun2();

    }

    private static void fun2() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.conn();
            statement = conn.createStatement();
            String sql = "select id,name,price from book";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal(3);
                System.out.println("id: " + id + ",name: " + name + ",price: " + price);
            }
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(resultSet,statement,conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void fun1() {
        //准备SQL；演示基本的增删改
        //String sql = "insert into book values (null,'java',15.55)";
        //String sql = "delete from book where id = 1";
        String sql = "update book set name = 'c' where id = 2";
        try {
            DBUtils.update(sql);
        } catch (DBUtilsException e) {
            e.printStackTrace();
        }
    }
}
