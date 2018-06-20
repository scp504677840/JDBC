package main;

import exception.DBUtilsException;
import utils.DaoUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 区分：函数有返回值，存储过程没有。
 * 调用函数：
 * {？= call <procedure-name> [（<arg1>，<arg2>，...）]}
 * <p>
 * 调用存储过程：
 * {call <procedure-name> [（<arg1>，<arg2>，...）]}
 */
public class Call {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement statement = null;
        try {

            //1.CallableStatement
            connection = DaoUtils.connection();
            String sql = "{?= call sum_salary(?,?)}";
            statement = connection.prepareCall(sql);

            //2.设置Out
            statement.registerOutParameter(1, Types.NUMERIC);
            statement.registerOutParameter(3, Types.NUMERIC);

            //3.设置In或In Out
            statement.setInt(2, 80);

            //4.执行函数或存储过程
            statement.execute();

            //5.获取结果
            double sumSalary = statement.getDouble(1);
            long empCount = statement.getLong(3);

            System.out.println("sumSalary: " + sumSalary + ",empCount: " + empCount);
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, statement, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }
}
