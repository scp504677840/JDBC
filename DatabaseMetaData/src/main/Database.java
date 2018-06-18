package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Database {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此数据库产品的名称。
            //getDatabaseProductName
            String databaseProductName = metaData.getDatabaseProductName();
            System.out.println("databaseProductName: " + databaseProductName);

            //检索此数据库产品的版本号。
            //getDatabaseProductVersion
            String databaseProductVersion = metaData.getDatabaseProductVersion();
            System.out.println("databaseProductVersion: " + databaseProductVersion);

            //检索底层数据库的主要版本号。
            //getDatabaseMajorVersion
            int databaseMajorVersion = metaData.getDatabaseMajorVersion();
            System.out.println("databaseMajorVersion: " + databaseMajorVersion);

            //检索基础数据库的次要版本号。
            //getDatabaseMinorVersion
            int databaseMinorVersion = metaData.getDatabaseMinorVersion();
            System.out.println("databaseMinorVersion: " + databaseMinorVersion);

            //databaseProductName: MySQL
            //databaseProductVersion: 5.7.18
            //databaseMajorVersion: 5
            //databaseMinorVersion: 7

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
