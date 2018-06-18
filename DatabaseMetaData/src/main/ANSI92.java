package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class ANSI92 {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此数据库是否支持ANSI92入门级SQL语法。
            //supportsANSI92EntryLevelSQL
            boolean supportsANSI92EntryLevelSQL = metaData.supportsANSI92EntryLevelSQL();
            System.out.println("supportsANSI92EntryLevelSQL: " + supportsANSI92EntryLevelSQL);
            //supportsANSI92EntryLevelSQL: true

            //检索此数据库是否支持ANSI92中间SQL语法支持。
            //supportsANSI92IntermediateSQL
            boolean supportsANSI92IntermediateSQL = metaData.supportsANSI92IntermediateSQL();
            System.out.println("supportsANSI92IntermediateSQL: " + supportsANSI92IntermediateSQL);
            //supportsANSI92IntermediateSQL: false

            //检索此数据库是否支持ANSI92完整SQL语法支持。
            //supportsANSI92FullSQL
            boolean supportsANSI92FullSQL = metaData.supportsANSI92FullSQL();
            System.out.println("supportsANSI92FullSQL: " + supportsANSI92FullSQL);
            //supportsANSI92FullSQL: false

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
