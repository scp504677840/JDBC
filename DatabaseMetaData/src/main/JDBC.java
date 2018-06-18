package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class JDBC {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此驱动程序的主要JDBC版本号。
            //getJDBCMajorVersion
            int jdbcMajorVersion = metaData.getJDBCMajorVersion();
            System.out.println("jdbcMajorVersion: " + jdbcMajorVersion);
            //jdbcMajorVersion: 4

            //检索此驱动程序的次要JDBC版本号。
            //getJDBCMinorVersion
            int jdbcMinorVersion = metaData.getJDBCMinorVersion();
            System.out.println("jdbcMinorVersion: " + jdbcMinorVersion);
            //jdbcMinorVersion: 2

        } catch (DBUtilsException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(null, null, conn);
            } catch (DBUtilsException e) {
                e.printStackTrace();
            }
        }

    }
}
