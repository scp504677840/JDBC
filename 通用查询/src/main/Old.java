package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PreparedStatement
 * <p>
 * 1.创建的时候传入SQL。
 * 2.设置占位符的值。
 * 3.执行SQL。
 * <p>
 * 作用：
 * 防止SQL注入。
 */
public class Old {
    public static void main(String[] args) {

        //演示增加
        //insert();

        //演示修改
        //update();

        //演示删除
        //delete();

        //演示增加、修改、删除
        crud();

        //演示查询
        query();

    }

    /**
     * 查询
     */
    private static void query() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.conn();
            String sql = "select id,name,price from book where id > ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, 1);
            resultSet = statement.executeQuery();

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
                DBUtils.close(resultSet, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insert() {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.conn();
            String sql = "insert into book values (?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setObject(1, null);
            statement.setString(2, "kotlin");
            statement.setBigDecimal(3, new BigDecimal(1.5));
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void update() {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.conn();
            String sql = "update book set price = ? where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setBigDecimal(1, new BigDecimal(99));
            statement.setInt(2, 2);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void delete() {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.conn();
            String sql = "delete from book where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, 7);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void crud() {
        //String sql = "insert into book values (?,?,?)";
        String sql = "update book set price = ? where id = ?";
        //String sql = "delete from book where id = ?";
        try {
            DBUtils.update(sql, new BigDecimal(20), 5);
        } catch (DBUtilsException e) {
            e.printStackTrace();
        }
    }
}
