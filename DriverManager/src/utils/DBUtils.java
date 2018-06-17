package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private DBUtils() {
    }

    /**
     * 获取数据库连接【推荐】
     *
     * @return 数据库连接
     */
    public static Connection conn() {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}