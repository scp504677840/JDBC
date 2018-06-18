package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //getURL
            String url = metaData.getURL();
            System.out.println("url: " + url);
            //url: jdbc:mysql://192.168.0.105:3306/lab

            //getUserName
            String userName = metaData.getUserName();
            System.out.println("userName: " + userName);
            //root@180.173.51.156

            //检索此数据库是否处于只读模式。
            //isReadOnly
            boolean readOnly = metaData.isReadOnly();
            System.out.println("readOnly: " + readOnly);
            //readOnly: false

            //getSQLKeywords
            String sqlKeywords = metaData.getSQLKeywords();
            System.out.println("sqlKeywords: " + sqlKeywords + "\n,size: " + sqlKeywords.split(",").length);
            //size: 119

            //getSearchStringEscape
            //getExtraNameCharacters

            //supportsExpressionsInOrderBy
            //supportsOrderByUnrelated

            //supportsLikeEscapeClause

            //supportsIntegrityEnhancementFacility

            //supportsPositionedDelete
            //supportsPositionedUpdate
            //supportsSelectForUpdate

            //supportsUnion
            //supportsUnionAll

            //getMaxBinaryLiteralLength
            //getMaxCharLiteralLength

            //getMaxConnections

            //getMaxUserNameLength

            //getCrossReference
            //getTypeInfo

            //supportsBatchUpdates
            //getUDTs
            //getConnection
            //supportsSavepoints
            //supportsNamedParameters
            //supportsMultipleOpenResults

            //getSuperTypes

            //getAttributes

            //getSQLStateType
            //locatorsUpdateCopy

            //getClientInfoProperties

            //generatedKeyAlwaysReturned
            //getMaxLogicalLobSize

            //supportsSharding

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
