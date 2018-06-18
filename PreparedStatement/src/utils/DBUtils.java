package utils;

import exception.DBUtilsException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtils {

    private DBUtils() {
    }

    /**
     * 获取数据库连接【推荐】
     *
     * @return 数据库连接
     */
    public static Connection conn() throws DBUtilsException {
        try {

            //1.获取数据库连接属性
            Properties properties = new Properties();
            //InputStream in = DBUtils.class.getResourceAsStream("jdbc.properties");
            //properties.load(in);

            properties.load(new FileInputStream("/Users/scp/Documents/Workspace/Java/JLab/src/jdbc.properties"));

            //2.准备数据库连接信息
            String driverClassName = properties.getProperty("driver");
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            //3. 注册数据库连接驱动，下面有两种写法，效果是一样的，推荐使用第二种写法。
            //3.1 完整写法，和下面的写法效果完全一样。
            //Driver driver = (Driver) Class.forName(driverClassName).getConstructor().newInstance();
            //DriverManager.registerDriver(driver);
            //3.2 简写，内部静态代码块实现和上面是一样的。【推荐】
            Class.forName(driverClassName);

            //4.通过DriverManager来获取数据库连接
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new DBUtilsException(e);
        }
    }

    /**
     * 执行增删改SQL语句
     *
     * @param sql 增删改SQL语句
     * @return 受影响的行数
     * @throws DBUtilsException 数据库工具类异常
     */
    public static int update(String sql) throws DBUtilsException {
        Connection conn = conn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DBUtilsException(e);
        } finally {
            close(null, statement, conn);
        }
    }

    /**
     * 使用PreparedStatement执行增删改SQL语句
     *
     * @param sql 增删改SQL语句
     * @return 受影响的行数
     * @throws DBUtilsException 数据库工具类异常
     */
    public static int update(String sql, Object... params) throws DBUtilsException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //1.获取数据库连接
            conn = DBUtils.conn();

            //2.创建预定义SQL
            statement = conn.prepareStatement(sql);

            //3.设置预定义值
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            //4.执行SQL
            return statement.executeUpdate();
        } catch (DBUtilsException | SQLException e) {
            throw new DBUtilsException(e);
        } finally {
            close(null, statement, conn);
        }
    }

    /**
     * 关闭数据库资源
     *
     * @param resultSet 查询结果
     * @param statement Statement
     * @param conn      数据库连接
     * @throws DBUtilsException 数据库工具类异常
     */
    public static void close(ResultSet resultSet, Statement statement, Connection conn) throws DBUtilsException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DBUtilsException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DBUtilsException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DBUtilsException(e);
            }
        }
    }

}