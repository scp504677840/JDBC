package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Subqueries {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsSubqueriesInComparisons
            boolean supportsSubqueriesInComparisons = metaData.supportsSubqueriesInComparisons();
            System.out.println("supportsSubqueriesInComparisons: " + supportsSubqueriesInComparisons);

            //supportsSubqueriesInExists
            //supportsSubqueriesInIns
            //supportsSubqueriesInQuantifieds
            //supportsCorrelatedSubqueries

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
