package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Nulls {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //nullsAreSortedHigh
            boolean nullsAreSortedHigh = metaData.nullsAreSortedHigh();
            System.out.println("nullsAreSortedHigh: " + nullsAreSortedHigh);

            //nullsAreSortedLow
            //nullsAreSortedAtStart
            //nullsAreSortedAtEnd
            //nullPlusNonNullIsNull

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
