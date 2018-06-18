package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class GroupBy {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsGroupBy
            boolean supportsGroupBy = metaData.supportsGroupBy();
            System.out.println("supportsGroupBy: " + supportsGroupBy);

            //supportsGroupByUnrelated
            //supportsGroupByBeyondSelect

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
