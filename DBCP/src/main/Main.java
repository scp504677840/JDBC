package main;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接池
 *
 * 注意：
 * 需要配置DBCP和POOL
 * 1.http://commons.apache.org/proper/commons-dbcp/
 * 2.http://commons.apache.org/proper/commons-pool/
 */
public class Main {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://192.168.0.105/lab");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");


        //以LIFO的形式返回属性。
        //getLifo
        boolean lifo = dataSource.getLifo();
        System.out.println("lifo: " + lifo);
        //lifo: true

        //setLifo
        dataSource.setLifo(true);

        //返回可以同时分配的最大活动连接数。
        //getMaxTotal
        int maxTotal = dataSource.getMaxTotal();
        System.out.println("maxTotal: " + maxTotal);
        //maxTotal: 8

        //设置可以同时分配的最大活动连接数。
        //setMaxTotal
        dataSource.setMaxTotal(10);

        //返回池中可保持空闲的最大连接数。 超额闲置连接在返回池时被销毁。
        //负值表示没有限制
        //getMaxIdle
        int maxIdle = dataSource.getMaxIdle();
        System.out.println("maxIdle: " + maxIdle);
        //maxIdle: 8

        //setMaxIdle
        dataSource.setMaxIdle(10);

        //返回池中空闲连接的最小数量。
        //当运行空闲对象清除程序时，池会尝试确保minIdle连接可用。
        //除非timeBetweenEvictionRunsMillis具有正值，否则此属性的值不起作用。
        long timeBetweenEvictionRunsMillis = dataSource.getTimeBetweenEvictionRunsMillis();
        System.out.println("timeBetweenEvictionRunsMillis: " + timeBetweenEvictionRunsMillis);
        //timeBetweenEvictionRunsMillis: -1

        //设置timeBetweenEvictionRunsMillis
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 10);

        //getMinIdle
        int minIdle = dataSource.getMinIdle();
        System.out.println("minIdle: " + minIdle);
        //minIdle: 0

        //setMinIdle
        dataSource.setMinIdle(5);

        //返回连接池的初始大小。
        //getInitialSize
        int initialSize = dataSource.getInitialSize();
        System.out.println("initialSize: " + initialSize);
        //initialSize: 0

        //设置连接池的初始大小。
        //setInitialSize
        dataSource.setInitialSize(3);

        //返回池抛出异常之前等待连接返回的最大毫秒数。
        //getMaxWaitMillis
        long maxWaitMillis = dataSource.getMaxWaitMillis();
        System.out.println("maxWaitMillis: " + maxWaitMillis);
        //maxWaitMillis: -1

        //setMaxWaitMillis
        dataSource.setMaxWaitMillis(1000 * 30);

        //[只读]从此数据源分配的当前活动连接数。
        //getNumActive
        int numActive = dataSource.getNumActive();
        System.out.println("numActive: " + numActive);
        //numActive: 0

        //[只读]当前正在等待从此数据源分配的空闲连接数。
        //getNumIdle
        int numIdle = dataSource.getNumIdle();
        System.out.println("numIdle: " + numIdle);
        //numIdle: 0

        //以毫秒为单位返回连接的最大允许生命周期。
        //零或更小的值表示无限的生命周期。
        //getMaxConnLifetimeMillis
        long maxConnLifetimeMillis = dataSource.getMaxConnLifetimeMillis();
        System.out.println("maxConnLifetimeMillis: " + maxConnLifetimeMillis);
        //maxConnLifetimeMillis: -1

        //setMaxConnLifetimeMillis
        dataSource.setMaxConnLifetimeMillis(1000 * 60);

        //如果为true，则关闭此数据源，并且不能从此数据源检索更多连接。
        //isClosed
        boolean closed = dataSource.isClosed();
        System.out.println("closed: " + closed);
        //closed: false

        //关闭并释放当前存储在与此数据源关联的连接池中的所有空闲连接。
        //在调用此方法时检出到客户端的连接不受影响。
        //当客户端应用程序随后调用Connection.close（）将这些连接返回到池时，将关闭基础JDBC连接。
        //尝试在调用此方法后使用getConnection（）获取连接将导致SQLExceptions。
        //这个方法是幂等的 - 即关闭一个已经关闭的BasicDataSource不起作用，并且不会产生异常。
        //close
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
