package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class ResultSet {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsMultipleResultSets
            boolean supportsMultipleResultSets = metaData.supportsMultipleResultSets();
            System.out.println("supportsMultipleResultSets: " + supportsMultipleResultSets);

            //supportsResultSetType
            //supportsResultSetConcurrency
            //supportsResultSetHoldability
            //getResultSetHoldability
            //autoCommitFailureClosesAllResultSets

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
