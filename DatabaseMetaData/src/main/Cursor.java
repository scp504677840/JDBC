package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Cursor {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsOpenCursorsAcrossCommit
            boolean supportsOpenCursorsAcrossCommit = metaData.supportsOpenCursorsAcrossCommit();
            System.out.println("supportsOpenCursorsAcrossCommit: " + supportsOpenCursorsAcrossCommit);

            //supportsOpenCursorsAcrossRollback
            //getMaxCursorNameLength
            //supportsRefCursors

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
