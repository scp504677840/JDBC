package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Identifier {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //supportsMixedCaseIdentifiers
            boolean supportsMixedCaseIdentifiers = metaData.supportsMixedCaseIdentifiers();
            System.out.println("supportsMixedCaseIdentifiers: " + supportsMixedCaseIdentifiers);

            //storesUpperCaseIdentifiers
            //storesLowerCaseIdentifiers
            //storesMixedCaseIdentifiers
            //supportsMixedCaseQuotedIdentifiers
            //storesUpperCaseQuotedIdentifiers
            //storesLowerCaseQuotedIdentifiers
            //storesMixedCaseQuotedIdentifiers
            //getIdentifierQuoteString
            //getBestRowIdentifier

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
