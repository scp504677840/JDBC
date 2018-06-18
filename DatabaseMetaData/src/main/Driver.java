package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此JDBC驱动程序的名称。
            //getDriverName
            String driverName = metaData.getDriverName();
            System.out.println("driverName: " + driverName);

            //检索此JDBC驱动程序的版本号。
            //getDriverVersion
            String driverVersion = metaData.getDriverVersion();
            System.out.println("driverVersion: " + driverVersion);

            //检索此JDBC驱动程序的主要版本号。
            //getDriverMajorVersion
            int driverMajorVersion = metaData.getDriverMajorVersion();
            System.out.println("driverMajorVersion: " + driverMajorVersion);

            //检索此JDBC驱动程序的次要版本号。
            //getDriverMinorVersion
            int driverMinorVersion = metaData.getDriverMinorVersion();
            System.out.println("driverMinorVersion: " + driverMinorVersion);

            //driverName: MySQL Connector/J
            //driverVersion: mysql-connector-java-8.0.11 (Revision: 6d4eaa273bc181b4cf1c8ad0821a2227f116fedf)
            //driverMajorVersion: 8
            //driverMinorVersion: 0

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
