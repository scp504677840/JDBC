package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.ReflectUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCDao<T> implements Dao<T> {

    private final QueryRunner queryRunner;
    private Class<T> type;

    public JDBCDao() {
        queryRunner = new QueryRunner();

        Class<? extends JDBCDao> clazz = getClass();
        System.out.println("clazz: " + clazz);

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println("genericSuperclass: " + genericSuperclass);

        ParameterizedType pt = (ParameterizedType) genericSuperclass;
        System.out.println("pt: " + pt);

        Type actualTypeArgument = pt.getActualTypeArguments()[0];
        System.out.println("actualTypeArgument: " + actualTypeArgument);

        type = ReflectUtils.getGenericTypeSuperclass(getClass());
    }

    /**
     * 增加、更新、删除
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 受影响的行数
     * @throws SQLException 数据库异常
     */
    @Override
    public int update(Connection connection, String sql, Object... params) throws SQLException {
        return queryRunner.update(connection, sql, params);
    }

    /**
     * 获取单个查询结果
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 对象
     * @throws SQLException 数据库异常
     */
    @Override
    public T get(Connection connection, String sql, Object... params) throws SQLException {
        return queryRunner.query(connection, sql, new BeanHandler<>(type), params);
    }

    /**
     * 获取多个查询结果
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 对象集合
     * @throws SQLException 数据库异常
     */
    @Override
    public List<T> list(Connection connection, String sql, Object... params) throws SQLException {
        return queryRunner.query(connection, sql, new BeanListHandler<>(type), params);
    }

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
    @Override
    public <E> E getSingleValue(Connection connection, String sql, Object... params) throws SQLException {
        return queryRunner.query(connection, sql, new ScalarHandler<>(), params);
    }

    /**
     * 批量处理SQL
     *
     * @param connection 数据库连接
     * @param sql        SQL语句
     * @param params     填充占位符的可变参数
     * @return 批量处理受影响的行数
     * @throws SQLException 数据库异常
     */
    @Override
    public int[] batch(Connection connection, String sql, Object[]... params) throws SQLException {
        return queryRunner.batch(connection, sql, params);
    }
}
