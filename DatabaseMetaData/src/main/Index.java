package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Index {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //getMaxIndexLength
            int maxIndexLength = metaData.getMaxIndexLength();
            System.out.println("maxIndexLength: " + maxIndexLength);

            //getIndexInfo
            //ResultSet indexInfo = metaData.getIndexInfo();
            //System.out.println("indexInfo: " + indexInfo);

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
