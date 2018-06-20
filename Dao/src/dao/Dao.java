package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库基础接口
 *
 * @param <T> 实例类型
 */
public interface Dao<T> {

    /**
     * 增加、更新、删除
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 受影响的行数
     * @throws SQLException 数据库异常
     */
    int update(Connection connection, String sql, Object... params) throws SQLException;

    /**
     * 获取单个查询结果
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 对象
     * @throws SQLException 数据库异常
     */
    T get(Connection connection, String sql, Object... params) throws SQLException;

    /**
     * 获取多个查询结果
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 对象集合
     * @throws SQLException 数据库异常
     */
    List<T> list(Connection connection, String sql, Object... params) throws SQLException;

    /**
     * 获取单个值
     * 如：ID、姓名、总和
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @param <E>        元素类型
     * @return 获取单个值
     * @throws SQLException 数据库异常
     */
    <E> E getSingleValue(Connection connection, String sql, Object... params) throws SQLException;

    /**
     * 批量处理SQL
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 批量处理受影响的行数
     * @throws SQLException 数据库异常
     */
    int[] batch(Connection connection, String sql, Object[]... params) throws SQLException;

}
