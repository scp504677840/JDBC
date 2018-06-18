package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreVisible {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DBUtils.conn();

            DatabaseMetaData metaData = conn.getMetaData();

            //ResultSet.TYPE_FORWARD_ONLY
            //ResultSet.TYPE_SCROLL_INSENSITIVE
            //ResultSet.TYPE_SCROLL_SENSITIVE
            //static int	TYPE_FORWARD_ONLY
            //该常数表示ResultSet光标可能仅向前移动的对象的类型。
            //static int	TYPE_SCROLL_INSENSITIVE
            //该常数指示ResultSet可滚动的对象的类型，但通常对对基础数据的更改不敏感ResultSet。
            //static int	TYPE_SCROLL_SENSITIVE
            //该常数指示ResultSet可滚动的对象的类型，并且通常对基础数据变化敏感ResultSet。

            //检索给定类型的ResultSet对象是否可见结果集自己的更新。
            //ownUpdatesAreVisible
            boolean ownUpdatesAreVisible = metaData.ownUpdatesAreVisible(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("ownUpdatesAreVisible: " + ownUpdatesAreVisible);

            //检索结果集自己的删除是否可见。
            //ownDeletesAreVisible
            boolean ownDeletesAreVisible = metaData.ownDeletesAreVisible(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("ownDeletesAreVisible: " + ownDeletesAreVisible);

            //检索结果集自己的插入是否可见。
            //ownInsertsAreVisible
            boolean ownInsertsAreVisible = metaData.ownInsertsAreVisible(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("ownInsertsAreVisible: " + ownInsertsAreVisible);

            //检索其他人所做的更新是否可见。
            //othersUpdatesAreVisible
            boolean othersUpdatesAreVisible = metaData.othersUpdatesAreVisible(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("othersUpdatesAreVisible: " + othersUpdatesAreVisible);

            //检索其他人所做的删除是否可见。
            //othersDeletesAreVisible
            boolean othersDeletesAreVisible = metaData.othersDeletesAreVisible(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("othersDeletesAreVisible: " + othersDeletesAreVisible);

            //检索其他人所做的插入是否可见。
            //othersInsertsAreVisible
            boolean othersInsertsAreVisible = metaData.othersInsertsAreVisible(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("othersInsertsAreVisible: " + othersInsertsAreVisible);

            //updatesAreDetected
            boolean updatesAreDetected = metaData.updatesAreDetected(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("updatesAreDetected: " + updatesAreDetected);

            //deletesAreDetected
            boolean deletesAreDetected = metaData.deletesAreDetected(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("deletesAreDetected: " + deletesAreDetected);

            //insertsAreDetected
            boolean insertsAreDetected = metaData.insertsAreDetected(ResultSet.TYPE_FORWARD_ONLY);
            System.out.println("insertsAreDetected: " + insertsAreDetected);

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
