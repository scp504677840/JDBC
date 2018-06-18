package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Statement {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsOpenStatementsAcrossCommit
            boolean supportsOpenStatementsAcrossCommit = metaData.supportsOpenStatementsAcrossCommit();
            System.out.println("supportsOpenStatementsAcrossCommit: " + supportsOpenStatementsAcrossCommit);

            //supportsOpenStatementsAcrossRollback
            //getMaxStatementLength
            //getMaxStatements
            //supportsStatementPooling

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
