package utils;

import exception.DBUtilsException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

public class DBUtils {

    /**
     * 数据库连接池数据源
     */
    private static DataSource dataSource;

    static {
        try {
            //获取数据库连接属性
            Properties properties = new Properties();
            InputStream in = DBUtils.class.getResourceAsStream("jdbc.properties");
            properties.load(in);

            //properties.load(new FileInputStream("/Users/scp/Documents/Workspace/Java/JLab/src/jdbc.properties"));

            //创建数据库数据源
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DBUtils() {
    }

    /**
     * 获取数据库连接【推荐】
     *
     * @return 数据库连接
     */
    public static Connection connection() throws DBUtilsException {
        try {
            //创建数据库连接
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DBUtilsException(e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public static Connection conn() throws DBUtilsException {
        try {

            //1.获取数据库连接属性
            Properties properties = new Properties();
            InputStream in = DBUtils.class.getResourceAsStream("jdbc.properties");
            properties.load(in);

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
     * 通用查询
     *
     * @param clazz  结果集的类型
     * @param sql    SQl
     * @param params SQL预定义参数
     * @param <T>    结果集类型
     * @return 结果集
     * @throws DBUtilsException 数据库工具类异常
     */
    public static <T> List<T> query(Class<T> clazz, String sql, Object... params) throws DBUtilsException {
        // 数据库连接
        Connection conn = conn();
        // 预定义SQl
        PreparedStatement statement = null;
        // 结果集
        ResultSet resultSet = null;

        try {
            // 创建预定义SQL
            statement = conn.prepareStatement(sql);
            // 遍历给预定义SQL中的预定义参数赋值
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            // 执行SQL得到结果集
            resultSet = statement.executeQuery();
            // 获取结果集的元数据；如：列名、列的别名。。。
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 定义存储结果集的Map
            List<Map<String, Object>> values = new ArrayList<>();

            // 遍历结果集
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                // 遍历元数据中所查询的列
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 获取列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    // 获取列的值
                    Object columnValue = resultSet.getObject(columnLabel);
                    // 存储到Map中
                    map.put(columnLabel, columnValue);
                }
                // 添加到集合中
                values.add(map);
            }

            // 创建返回结果集
            List<T> result = new ArrayList<>();

            // 遍历查询结果集
            for (Map<String, Object> map : values) {
                // 反射创建类的实例
                T t = clazz.getConstructor().newInstance();
                // 给实例属性赋值
                BeanUtils.populate(t, map);
                // 添加返回结果集中
                result.add(t);
            }

            // 返回结果集
            return result;
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException
                | SQLException e) {
            throw new DBUtilsException(e);
        } finally {
            close(resultSet, statement, conn);
        }
    }

    /**
     * 插入数据并获取自动生成的主键的值
     *
     * @param sql    SQL语句
     * @param params 参数数据
     * @return 主键
     * @throws DBUtilsException 工具类内部产生错误
     */
    public static int insert(String sql, Object... params) throws DBUtilsException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            //1.获取数据库连接和Statement
            conn = conn();
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //2.给预定义参数赋值
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            //3.执行SQL
            statement.executeUpdate();

            //4.获取自动生成的主键
            generatedKeys = statement.getGeneratedKeys();
            //5.当有自动生成的主键
            if (generatedKeys.next()) {
                BigInteger key = (BigInteger) generatedKeys.getObject(1);
                return key.intValue();
            }

            //6.当没有自动生成主键时返回BigInteger.ZERO
            return -1;
        } catch (DBUtilsException | SQLException e) {
            throw new DBUtilsException(e);
        } finally {
            close(generatedKeys, statement, conn);
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
     * 开始事务
     *
     * @param connection 数据库连接
     * @throws DBUtilsException 工具类异常
     */
    public static void beginTransaction(Connection connection) throws DBUtilsException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DBUtilsException(e);
        }
    }

    /**
     * 回滚事务
     *
     * @param connection 数据库连接
     * @throws DBUtilsException 工具类异常
     */
    public static void rollback(Connection connection) throws DBUtilsException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DBUtilsException(e);
        }
    }

    /**
     * 提交事务
     *
     * @param connection 数据库连接
     * @throws DBUtilsException 工具类异常
     */
    public static void commitTransaction(Connection connection) throws DBUtilsException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DBUtilsException(e);
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