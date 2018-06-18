package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.*;

public class Catalog {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索数据库供应商对“目录”的首选术语。
            //getCatalogTerm
            String catalogTerm = metaData.getCatalogTerm();
            System.out.println("catalogTerm: " + catalogTerm);
            //catalogTerm: database

            //检索某个目录是否出现在完全限定的表名称的开头。
            //isCatalogAtStart
            boolean isCatalogAtStart = metaData.isCatalogAtStart();
            System.out.println("isCatalogAtStart: " + isCatalogAtStart);
            //isCatalogAtStart: true

            //检索此数据库用作目录和表名称之间的分隔符。
            //getCatalogSeparator
            String catalogSeparator = metaData.getCatalogSeparator();
            System.out.println("catalogSeparator: " + catalogSeparator);
            //catalogSeparator: .

            //检索是否可以在数据操作语句中使用目录名称。
            //supportsCatalogsInDataManipulation
            boolean supportsCatalogsInDataManipulation = metaData.supportsCatalogsInDataManipulation();
            System.out.println("supportsCatalogsInDataManipulation: " + supportsCatalogsInDataManipulation);
            //supportsCatalogsInDataManipulation: true

            //检索过程调用语句中是否可以使用目录名称。
            //supportsCatalogsInProcedureCalls
            boolean supportsCatalogsInProcedureCalls = metaData.supportsCatalogsInProcedureCalls();
            System.out.println("supportsCatalogsInProcedureCalls: " + supportsCatalogsInProcedureCalls);
            //supportsCatalogsInProcedureCalls: true

            //检索是否可以在表定义语句中使用目录名称。
            //supportsCatalogsInTableDefinitions
            boolean supportsCatalogsInTableDefinitions = metaData.supportsCatalogsInTableDefinitions();
            System.out.println("supportsCatalogsInTableDefinitions: " + supportsCatalogsInTableDefinitions);
            //supportsCatalogsInTableDefinitions: true

            //检索是否可以在索引定义语句中使用目录名称。
            //supportsCatalogsInIndexDefinitions
            boolean supportsCatalogsInIndexDefinitions = metaData.supportsCatalogsInIndexDefinitions();
            System.out.println("supportsCatalogsInIndexDefinitions: " + supportsCatalogsInIndexDefinitions);
            //supportsCatalogsInIndexDefinitions: true

            //检索是否可以在特权定义语句中使用目录名称。
            //supportsCatalogsInPrivilegeDefinitions
            boolean supportsCatalogsInPrivilegeDefinitions = metaData.supportsCatalogsInPrivilegeDefinitions();
            System.out.println("supportsCatalogsInPrivilegeDefinitions: " + supportsCatalogsInPrivilegeDefinitions);
            //supportsCatalogsInPrivilegeDefinitions: true

            //检索此数据库在目录名称中允许的最大字符数。
            //getMaxCatalogNameLength
            int maxCatalogNameLength = metaData.getMaxCatalogNameLength();
            System.out.println("maxCatalogNameLength: " + maxCatalogNameLength);
            //maxCatalogNameLength: 32

            //检索此数据库中可用的目录名称。
            //getCatalogs
            ResultSet catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                ResultSetMetaData md = catalogs.getMetaData();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnName = md.getColumnName(i + 1);
                    Object object = catalogs.getObject(i + 1);
                    System.out.println("columnName: " + columnName + ",object: " + object);
                }
            }
            //columnName: TABLE_CAT,object: information_schema
            //columnName: TABLE_CAT,object: lab
            //columnName: TABLE_CAT,object: mysql
            //columnName: TABLE_CAT,object: performance_schema
            //columnName: TABLE_CAT,object: sys

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
