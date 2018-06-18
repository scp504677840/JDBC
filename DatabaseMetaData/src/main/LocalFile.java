package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalFile {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此数据库是否为每个表使用文件。
            //usesLocalFiles
            boolean usesLocalFiles = metaData.usesLocalFiles();
            System.out.println("usesLocalFiles: " + usesLocalFiles);
            //usesLocalFiles: false

            //检索此数据库是否将表存储在本地文件中。
            //usesLocalFilePerTable
            boolean usesLocalFilePerTable = metaData.usesLocalFilePerTable();
            System.out.println("usesLocalFilePerTable: " + usesLocalFilePerTable);
            //usesLocalFilePerTable: false

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
