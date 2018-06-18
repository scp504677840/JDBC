package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Table {

    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索当前用户是否可以getTables在SELECT 语句中使用该方法返回的所有表。
            //allTablesAreSelectable
            boolean allTablesAreSelectable = metaData.allTablesAreSelectable();
            System.out.println("allTablesAreSelectable: " + allTablesAreSelectable);
            //allTablesAreSelectable: false

            //检索此数据库是否支持表关联名称。
            //supportsTableCorrelationNames
            boolean supportsTableCorrelationNames = metaData.supportsTableCorrelationNames();
            System.out.println("supportsTableCorrelationNames: " + supportsTableCorrelationNames);
            //supportsTableCorrelationNames: true

            //检索当表格相关名称被支持时，它们被限制为不同于表格的名称。
            //supportsDifferentTableCorrelationNames
            boolean supportsDifferentTableCorrelationNames = metaData.supportsDifferentTableCorrelationNames();
            System.out.println("supportsDifferentTableCorrelationNames: " + supportsDifferentTableCorrelationNames);
            //supportsDifferentTableCorrelationNames: true

            //检索是否可以在表定义语句中使用模式名称。
            //supportsSchemasInTableDefinitions
            boolean supportsSchemasInTableDefinitions = metaData.supportsSchemasInTableDefinitions();
            System.out.println("supportsSchemasInTableDefinitions: " + supportsSchemasInTableDefinitions);
            //supportsSchemasInTableDefinitions: false

            //在表名中检索此数据库允许的最大字符数。
            //getMaxTableNameLength
            int maxTableNameLength = metaData.getMaxTableNameLength();
            System.out.println("maxTableNameLength: " + maxTableNameLength);
            //maxTableNameLength: 64

            //检索此数据库在SELECT语句中允许的最大表数 。
            //getMaxTablesInSelect
            int maxTablesInSelect = metaData.getMaxTablesInSelect();
            System.out.println("maxTablesInSelect: " + maxTablesInSelect);
            //maxTablesInSelect: 256

            //检索给定目录中可用表格的描述。
            //getTables
            //ResultSet tables = metaData.getTables();
            //System.out.println("tables: " + tables);

            //检索此数据库中可用的表类型。
            //getTableTypes
            //ResultSet tableTypes = metaData.getTableTypes();
            //System.out.println("tableTypes: " + tableTypes);

            //检索目录中每个表的访问权限说明。
            //getTablePrivileges
            //ResultSet tablePrivileges = metaData.getTablePrivileges();
            //System.out.println("tablePrivileges: " + tablePrivileges);

            //检索此数据库中特定模式中定义的表层次结构的描述。
            //getSuperTables
            //ResultSet superTables = metaData.getSuperTables();
            //System.out.println("superTables: " + superTables);

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
