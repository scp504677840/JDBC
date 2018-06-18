package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 事务
 */
public class Transaction {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsMultipleTransactions
            boolean supportsMultipleTransactions = metaData.supportsMultipleTransactions();
            System.out.println("supportsMultipleTransactions: " + supportsMultipleTransactions);

            //getDefaultTransactionIsolation
            //supportsTransactions
            //supportsTransactionIsolationLevel
            //supportsDataDefinitionAndDataManipulationTransactions
            //supportsDataManipulationTransactionsOnly
            //dataDefinitionCausesTransactionCommit
            //dataDefinitionIgnoredInTransactions

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
