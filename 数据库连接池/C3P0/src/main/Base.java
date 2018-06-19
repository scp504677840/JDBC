package main;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接池
 * <p>
 * 注意：
 * 需要配置C3P0
 * 1.https://github.com/swaldman/c3p0
 */
public class Base {
    public static void main(String[] args) {
        ComboPooledDataSource dataSource = null;
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://192.168.0.105:3306/lab");
            dataSource.setUser("root");
            dataSource.setPassword("123456");

            //[可选]如果要打开PreparedStatement池，则还必须设置maxStatements 和/或maxStatementsPerConnection（均默认为0）：
            dataSource.setMaxStatements(180);

            Connection connection = dataSource.getConnection();
            System.out.println("connection: " + connection);
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }
}
