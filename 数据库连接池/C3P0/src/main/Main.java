package main;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            DataSource ds_unpooled = DataSources.unpooledDataSource("jdbc:mysql://192.168.0.105:3306/lab", "root", "123456");

            //方式一：
            //DataSource ds_pooled = DataSources.pooledDataSource(ds_unpooled);
            //System.out.println(connection);
            //[wrapping: com.mysql.cj.jdbc.ConnectionImpl@6e509ffa]

            //方式二：
            /*Map<String, String> overrideProps = new HashMap<>();
            overrideProps.put("driverClass", "com.mysql.cj.jdbc.Driver");
            DataSource ds_pooled = DataSources.pooledDataSource(ds_unpooled, overrideProps);

            Connection connection = ds_pooled.getConnection();
            System.out.println(connection);*/
            //com.mysql.cj.jdbc.Driver

            //方式三：
            ComboPooledDataSource dataSource = new ComboPooledDataSource("c3p0app");
            System.out.println(dataSource.getMaxPoolSize());
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
