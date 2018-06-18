package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Procedure {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //检索当前用户是否可以调用方法<code> getProcedures </ code>返回的所有过程。
            //allProceduresAreCallable
            //boolean callable = metaData.allProceduresAreCallable();
            //System.out.println("callable: " + callable);

            //getProcedureTerm
            String procedureTerm = metaData.getProcedureTerm();
            System.out.println("procedureTerm: " + procedureTerm);

            //supportsStoredProcedures
            //getMaxProcedureNameLength
            //getProcedures

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
