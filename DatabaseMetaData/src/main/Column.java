package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.*;

public class Column {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索此数据库是否支持ALTER TABLE 添加列。
            //supportsAlterTableWithAddColumn
            boolean supportsAlterTableWithAddColumn = metaData.supportsAlterTableWithAddColumn();
            System.out.println("supportsAlterTableWithAddColumn: " + supportsAlterTableWithAddColumn);
            //supportsAlterTableWithAddColumn: true

            //检索此数据库是否支持ALTER TABLE 删除列。
            //supportsAlterTableWithDropColumn
            boolean supportsAlterTableWithDropColumn = metaData.supportsAlterTableWithDropColumn();
            System.out.println("supportsAlterTableWithDropColumn: " + supportsAlterTableWithDropColumn);
            //supportsAlterTableWithDropColumn: true

            //检索此数据库是否支持列别名。
            //supportsColumnAliasing
            boolean supportsColumnAliasing = metaData.supportsColumnAliasing();
            System.out.println("supportsColumnAliasing: " + supportsColumnAliasing);
            //supportsColumnAliasing: true

            //检索此数据库中的列是否可以定义为不可空。
            //supportsNonNullableColumns
            boolean supportsNonNullableColumns = metaData.supportsNonNullableColumns();
            System.out.println("supportsNonNullableColumns: " + supportsNonNullableColumns);
            //supportsNonNullableColumns: true

            //检索此数据库允许列名称的最大字符数。
            //getMaxColumnNameLength
            int maxColumnNameLength = metaData.getMaxColumnNameLength();
            System.out.println("maxColumnNameLength: " + maxColumnNameLength);
            //maxColumnNameLength: 64

            //检索此数据库在GROUP BY子句中允许的最大列数 。
            //getMaxColumnsInGroupBy
            int maxColumnsInGroupBy = metaData.getMaxColumnsInGroupBy();
            System.out.println("maxColumnsInGroupBy: " + maxColumnsInGroupBy);
            //maxColumnsInGroupBy: 64

            //检索此数据库在索引中允许的最大列数。
            //getMaxColumnsInIndex
            int maxColumnsInIndex = metaData.getMaxColumnsInIndex();
            System.out.println("maxColumnsInIndex: " + maxColumnsInIndex);
            //maxColumnsInIndex: 16

            //检索此数据库在ORDER BY子句中允许的最大列数 。
            //getMaxColumnsInOrderBy
            int maxColumnsInOrderBy = metaData.getMaxColumnsInOrderBy();
            System.out.println("maxColumnsInOrderBy: " + maxColumnsInOrderBy);
            //maxColumnsInOrderBy: 64

            //检索数据库在SELECT列表中允许的最大列数 。
            //getMaxColumnsInSelect
            int maxColumnsInSelect = metaData.getMaxColumnsInSelect();
            System.out.println("maxColumnsInSelect: " + maxColumnsInSelect);
            //maxColumnsInSelect: 256

            //检索此数据库在表中允许的最大列数。
            //getMaxColumnsInTable
            int maxColumnsInTable = metaData.getMaxColumnsInTable();
            System.out.println("maxColumnsInTable: " + maxColumnsInTable);
            //maxColumnsInTable: 512

            //检索给定目录的存储过程参数和结果列的描述。
            //getProcedureColumns
            //ResultSet procedureColumns = metaData.getProcedureColumns();
            //System.out.println("procedureColumns: " + procedureColumns);

            //检索指定目录中可用表格列的描述。
            //getColumns
            ResultSet columns = metaData.getColumns(conn.getCatalog(), "lab", "book", "name");
            while (columns.next()) {
                ResultSetMetaData md = columns.getMetaData();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnLabel = md.getColumnLabel(i + 1);
                    System.out.println("columnLabel: " + columnLabel);
                }
            }
            System.out.println("columns: " + columns);

            //检索表格列的访问权限说明。
            //getColumnPrivileges
            //ResultSet columnPrivileges = metaData.getColumnPrivileges();
            //System.out.println("columnPrivileges: " + columnPrivileges);

            //检索在更新行中的任何值时自动更新的表格列的描述。
            //getVersionColumns
            //ResultSet versionColumns = metaData.getVersionColumns();
            //System.out.println("versionColumns: " + versionColumns);

            //检索给定目录的系统或用户功能参数和返回类型的描述。
            //getFunctionColumns
            //ResultSet functionColumns = metaData.getFunctionColumns();
            //System.out.println("functionColumns: " + functionColumns);

            //检索指定目录和模式内给定表中可用的伪列或隐藏列的描述。
            //getPseudoColumns
            //ResultSet pseudoColumns = metaData.getPseudoColumns();
            //System.out.println("pseudoColumns: " + pseudoColumns);

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
