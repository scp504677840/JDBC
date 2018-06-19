package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.sql.Connection;

/**
 * 连接池
 * <p>
 * 注意：
 * 需要配置DBCP和POOL
 * 1.http://commons.apache.org/proper/commons-dbcp/
 * 2.http://commons.apache.org/proper/commons-pool/
 */
public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtils.connection();
            System.out.println("connection: " + conn);
        } catch (DBUtilsException e) {
            e.printStackTrace();
        }
    }
}
