package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * -- auto-generated definition
 * create table bank
 * (
 * id      int auto_increment,
 * balance double default '0' not null,
 * constraint bank_id_uindex
 * unique (id)
 * );
 * <p>
 * alter table bank
 * add primary key (id);
 */
public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBUtils.conn();

            // 开始事务：取消自动提交事务
            conn.setAutoCommit(false);

            // 获取事务隔离级别
            int transactionIsolation = conn.getTransactionIsolation();
            System.out.println("事务隔离级别: " + transactionIsolation);
            // 事务隔离级别: 4
            // TRANSACTION_REPEATABLE_READ 可重复读

            // 设置事务隔离级别
            // 无
            //conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
            // 读未提交
            //conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            // 读已提交【推荐】
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 可重复读
            //conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            // 序列化
            //conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sql = "update bank set balance = balance - ? where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setDouble(1, 500.00);
            statement.setInt(2, 1);
            statement.executeUpdate();

            // 故意制造异常，触发回滚。
            //int i = 1 / 0;

            sql = "update bank set balance = balance + ? where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setDouble(1, 500.00);
            statement.setInt(2, 2);
            statement.executeUpdate();

            // 提交事务
            conn.commit();
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
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
