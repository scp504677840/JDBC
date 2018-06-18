package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class Convert {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsConvert
            boolean supportsConvert = metaData.supportsConvert();
            System.out.println("supportsConvert: " + supportsConvert);

            //supportsConvert
            //boolean supportsConvert = metaData.supportsConvert(Types.BLOB, Types.VARCHAR);
            //System.out.println("supportsConvert: " + supportsConvert);

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
