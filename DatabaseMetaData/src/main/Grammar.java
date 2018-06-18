package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Grammar {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此数据库是否支持ODBC最小SQL语法。
            //supportsMinimumSQLGrammar
            boolean supportsMinimumSQLGrammar = metaData.supportsMinimumSQLGrammar();
            System.out.println("supportsMinimumSQLGrammar: " + supportsMinimumSQLGrammar);

            //检索此数据库是否支持ODBC Core SQL语法。
            //supportsCoreSQLGrammar

            //检索此数据库是否支持ODBC扩展SQL语法。
            //supportsExtendedSQLGrammar

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
