package main;

import bean.Bank;
import dao.BankDao;
import dao.BankDaoImpl;
import exception.DBUtilsException;
import utils.DaoUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * -- auto-generated definition
 * create table bank
 * (
 * id      int auto_increment,
 * balance int default '0' not null,
 * constraint bank_id_uindex
 * unique (id)
 * );
 * <p>
 * alter table bank
 * add primary key (id);
 * <p>
 * 注意：
 * 1.http://commons.apache.org/proper/commons-beanutils/
 * 2.http://commons.apache.org/proper/commons-logging/
 * 3.http://commons.apache.org/proper/commons-dbcp/
 * 4.http://commons.apache.org/proper/commons-pool/
 * 5.http://commons.apache.org/proper/commons-dbutils/
 *
 * 特别说明：
 * 数据库连接池使用DBCP即可，C3P0可以不保留。
 */
public class Main {
    public static void main(String[] args) {

        //演示get方法，其他方法另行演示
        get();

    }

    private static void get() {
        Connection connection = null;

        try {
            connection = DaoUtils.connection();
            BankDao dao = new BankDaoImpl();

            String sql = "select * from bank where id = ?";
            Bank bank = dao.get(connection, sql, 3);
            System.out.println(bank);
        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    DaoUtils.close(null, null, connection);
                } catch (DBUtilsException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
