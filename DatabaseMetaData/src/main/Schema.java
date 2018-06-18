package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.*;

public class Schema {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索数据库供应商对“模式”的首选术语。
            //getSchemaTerm
            String schemaTerm = metaData.getSchemaTerm();
            System.out.println("schemaTerm: " + schemaTerm);
            //schemaTerm:

            //检索是否可以在数据操作语句中使用模式名称。
            //supportsSchemasInDataManipulation
            boolean supportsSchemasInDataManipulation = metaData.supportsSchemasInDataManipulation();
            System.out.println("supportsSchemasInDataManipulation: " + supportsSchemasInDataManipulation);
            //supportsSchemasInDataManipulation: false

            //检索过程调用语句中是否可以使用模式名称。
            //supportsSchemasInProcedureCalls
            boolean supportsSchemasInProcedureCalls = metaData.supportsSchemasInProcedureCalls();
            System.out.println("supportsSchemasInProcedureCalls: " + supportsSchemasInProcedureCalls);
            //supportsSchemasInProcedureCalls: false

            //检索是否可以在索引定义语句中使用模式名称。
            //supportsSchemasInIndexDefinitions
            boolean supportsSchemasInIndexDefinitions = metaData.supportsSchemasInIndexDefinitions();
            System.out.println("supportsSchemasInIndexDefinitions: " + supportsSchemasInIndexDefinitions);
            //supportsSchemasInIndexDefinitions: false

            //检索特权定义语句中是否可以使用模式名称。
            //supportsSchemasInPrivilegeDefinitions
            boolean supportsSchemasInPrivilegeDefinitions = metaData.supportsSchemasInPrivilegeDefinitions();
            System.out.println("supportsSchemasInPrivilegeDefinitions: " + supportsSchemasInPrivilegeDefinitions);
            //supportsSchemasInPrivilegeDefinitions: false

            //检索此数据库在模式名称中允许的最大字符数。
            //getMaxSchemaNameLength
            int maxSchemaNameLength = metaData.getMaxSchemaNameLength();
            System.out.println("maxSchemaNameLength: " + maxSchemaNameLength);
            //maxSchemaNameLength: 0

            //检索此数据库中可用的模式名称。
            //getSchemas
            ResultSet schemas = metaData.getSchemas();
            while (schemas.next()) {
                ResultSetMetaData md = schemas.getMetaData();
                for (int i = 0; i < md.getColumnCount(); i++) {
                    String columnLabel = md.getColumnLabel(i + 1);
                    Object columnValue = schemas.getObject(i + 1);
                    System.out.println("columnLabel: " + columnLabel + ",columnValue: " + columnValue);
                }
            }

            //检索此数据库中可用的模式名称。
            //getSchemas
            //ResultSet schemas = metaData.getSchemas(conn.getCatalog(), "lab");
            //System.out.println("schemas: " + schemas);

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
