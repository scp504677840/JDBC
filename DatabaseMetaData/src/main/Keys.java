package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.*;

public class Keys {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索给定表的主键列的描述。
            //getPrimaryKeys
            /*ResultSet primaryKeys = metaData.getPrimaryKeys(conn.getCatalog(), "lab", "book");
            while (primaryKeys.next()) {
                ResultSetMetaData md = primaryKeys.getMetaData();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnLabel = md.getColumnLabel(i + 1);
                    Object columnValue = primaryKeys.getObject(columnLabel);
                    System.out.println("columnLabel: " + columnLabel + ",columnValue: " + columnValue);
                }
            }*/
            //columnLabel: TABLE_CAT,columnValue: lab
            //columnLabel: TABLE_SCHEM,columnValue: null
            //columnLabel: TABLE_NAME,columnValue: book
            //columnLabel: COLUMN_NAME,columnValue: id
            //columnLabel: KEY_SEQ,columnValue: 1
            //columnLabel: PK_NAME,columnValue: PRIMARY

            //检索由给定表的外键列（由表导入的主键）引用的主键列的描述。
            //getImportedKeys
            /*ResultSet importedKeys = metaData.getImportedKeys(conn.getCatalog(), "lab", "book");
            while (importedKeys.next()) {
                ResultSetMetaData md = importedKeys.getMetaData();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnLabel = md.getColumnLabel(i + 1);
                    Object columnValue = importedKeys.getObject(columnLabel);
                    System.out.println("columnLabel: " + columnLabel + ",columnValue: " + columnValue);
                }
            }*/

            //检索引用给定表的主键列（由表导出的外键）的外键列的描述。
            //getExportedKeys
            ResultSet exportedKeys = metaData.getExportedKeys(conn.getCatalog(), "lab", "book");
            while (exportedKeys.next()) {
                ResultSetMetaData md = exportedKeys.getMetaData();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnLabel = md.getColumnLabel(i + 1);
                    Object columnValue = exportedKeys.getObject(columnLabel);
                    System.out.println("columnLabel: " + columnLabel + ",columnValue: " + columnValue);
                }
            }

            //检索语句执行后是否可以检索自动生成的键
            //supportsGetGeneratedKeys
            boolean supportsGetGeneratedKeys = metaData.supportsGetGeneratedKeys();
            System.out.println("supportsGetGeneratedKeys: " + supportsGetGeneratedKeys);
            //supportsGetGeneratedKeys: true

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
