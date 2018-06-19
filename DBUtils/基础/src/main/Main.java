package main;

import bean.Bank;
import exception.DBUtilsException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import utils.DaoUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * -- auto-generated definition
 * create table bank
 * (
 *   id      int auto_increment,
 *   balance int default '0' not null,
 *   constraint bank_id_uindex
 *   unique (id)
 * );
 *
 * alter table bank
 *   add primary key (id);
 *
 * 注意：
 * http://commons.apache.org/proper/commons-dbutils/
 */
public class Main {
    public static void main(String[] args) {

        //自定义转换
        //resultSetHandler();

        //将单个查询结果转换为JavaBean
        //beanHandler();

        //将多个查询结果转换为JavaBean集合
        //beanListHandler();

        //将单个查询结果的属性加入Array数组中
        //arrayHandler();

        //将多个查询结果每一行的结果加入到Array数组中，然后将Array数组加入到List集合中（所有行）。
        //arrayListHandler();

        //将单个查询结果转换为Map
        //mapHandler();

        //将多个查询结果的每一行转换为Map，然后将每一个Map加入List集合中（所有行）。
        //mapListHandler();

        //查询单个值
        //scalarHandler();

    }

    private static void scalarHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            //String sql = "select balance from bank where id = ?";
            //Integer balance = queryRunner.query(connection, sql, new ScalarHandler<Integer>(), 3);
            //System.out.println("balance: " + balance);
            //balance: 3333

            String sql = "select count(*) from bank";
            Long count = queryRunner.query(connection, sql, new ScalarHandler<>());
            System.out.println("count: " + count);
            //count: 5
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void mapListHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank";
            List<Map<String, Object>> list = queryRunner.query(connection, sql, new MapListHandler());

            list.forEach(stringObjectMap -> {
                stringObjectMap.forEach((key, value) -> {
                    System.out.print(key + ":" + value + " ");
                });
                System.out.println();
            });

            //id:1 balance:1111
            //id:2 balance:2222
            //id:3 balance:3333
            //id:4 balance:4444
            //id:5 balance:5555
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void mapHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank where id = ?";
            Map<String, Object> map = queryRunner.query(connection, sql, new MapHandler(), 5);

            map.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
            //id : 5
            //balance : 5555

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void arrayListHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank";
            List<Object[]> list = queryRunner.query(connection, sql, new ArrayListHandler());

            list.forEach(objects -> {
                for (Object object : objects) {
                    System.out.print(object + " ");
                }
                System.out.println();
            });
            //1 1111
            //2 2222
            //3 3333
            //4 4444
            //5 5555

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void arrayHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank where id = ?";
            Object[] objects = queryRunner.query(connection, sql, new ArrayHandler(), 5);

            for (Object object : objects) {
                System.out.println(object);
            }
            //5
            //5555

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void beanListHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank";
            List<Bank> banks = queryRunner.query(connection, sql, new BeanListHandler<>(Bank.class));

            banks.forEach(System.out::println);
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void beanHandler() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank where id = ?";
            Bank bank = queryRunner.query(connection, sql, new BeanHandler<>(Bank.class), 2);

            System.out.println(bank);

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void resultSetHandler() {
        Connection connection = null;
        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from bank where id = ?";

            ResultSetHandler<Bank> rsh = resultSet -> {

                if (!resultSet.next()) {
                    return null;
                }

                Bank bank = new Bank();
                ResultSetMetaData md = resultSet.getMetaData();
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnLabel = md.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(columnLabel);
                    map.put(columnLabel, columnValue);
                }
                try {
                    BeanUtils.populate(bank, map);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }

                return bank;
            };
            Bank bank = queryRunner.query(connection, sql, rsh, 1);

            System.out.println("bank: " + bank);

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void update() {
        Connection connection = null;
        try {
            connection = DaoUtils.connection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "update bank set balance = ? where id = ?";

            int count = queryRunner.update(connection, sql, 1111, 1);

            System.out.println("count: " + count);

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(null, null, connection);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }
    }
}
