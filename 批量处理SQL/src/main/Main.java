package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * -- auto-generated definition
 * create table bank
 * (
 * id      int auto_increment,
 * balance int default '0' not null,
 * constraint bank_id_uindex
 * unique (id)
 * );
 * <p>
 * alter table bank
 * add primary key (id);
 */
public class Main {
    public static void main(String[] args) {

        //31031
        //statement();

        //23108
        //preparedStatement();

        //23406
        //statementBatch();

        //15515；【推荐】
        preparedStatementBatch();

    }

    private static void preparedStatementBatch() {

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.conn();

            // 开始事务
            DBUtils.beginTransaction(conn);

            String sql = "insert into bank (balance) VALUES (?)";
            statement = conn.prepareStatement(sql);

            long start = System.currentTimeMillis();

            int length = 1000;
            for (int i = 0; i < length; i++) {
                statement.setDouble(1, (double) i);
                // 添加到批量执行
                statement.addBatch();
                // 当批量积累到300条时批量执行
                if ((i + 1) % 300 == 0) {
                    // 批量执行
                    statement.executeBatch();
                    // 清空之前积累到SQL
                    statement.clearBatch();
                }
            }

            // 若总条数不是批量数值的整数倍则需要额外再执行一次
            if (length % 300 != 0) {
                statement.executeBatch();
                statement.clearBatch();
            }

            // 提交事务
            DBUtils.commitTransaction(conn);

            long end = System.currentTimeMillis();

            System.out.println(end - start);
            //15515

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    DBUtils.rollback(conn);
                } catch (DBUtilsException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }

    }

    private static void statementBatch() {

        Connection conn = null;
        Statement statement = null;
        try {
            conn = DBUtils.conn();

            // 开始事务
            DBUtils.beginTransaction(conn);

            statement = conn.createStatement();

            long start = System.currentTimeMillis();

            int length = 1000;
            for (int i = 0; i < length; i++) {
                String sql = "insert into bank (balance) VALUES (" + i + ")";
                // 添加到批量执行
                statement.addBatch(sql);
                // 当批量积累到300条时批量执行
                if ((i + 1) % 300 == 0) {
                    // 批量执行
                    statement.executeBatch();
                    // 清空之前积累到SQL
                    statement.clearBatch();
                }
            }

            // 若总条数不是批量数值的整数倍则需要额外再执行一次
            if (length % 300 != 0) {
                statement.executeBatch();
                statement.clearBatch();
            }

            // 提交事务
            DBUtils.commitTransaction(conn);

            long end = System.currentTimeMillis();

            System.out.println(end - start);
            //23406

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    DBUtils.rollback(conn);
                } catch (DBUtilsException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }

    }

    private static void preparedStatement() {

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.conn();

            // 开始事务
            DBUtils.beginTransaction(conn);

            String sql = "insert into bank (balance) VALUES (?)";
            statement = conn.prepareStatement(sql);

            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                statement.setDouble(1, (double) i);
                statement.executeUpdate();
            }

            // 提交事务
            DBUtils.commitTransaction(conn);

            long end = System.currentTimeMillis();

            System.out.println(end - start);
            //23108

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    DBUtils.rollback(conn);
                } catch (DBUtilsException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }

    }

    private static void statement() {

        Connection conn = null;
        Statement statement = null;
        try {
            conn = DBUtils.conn();

            // 开始事务
            DBUtils.beginTransaction(conn);

            statement = conn.createStatement();

            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                String sql = "insert into bank (balance) VALUES (" + i + ")";
                statement.executeUpdate(sql);
            }

            // 提交事务
            DBUtils.commitTransaction(conn);

            long end = System.currentTimeMillis();

            System.out.println(end - start);
            //31031

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    DBUtils.rollback(conn);
                } catch (DBUtilsException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                DBUtils.close(null, statement, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }

    }
}
