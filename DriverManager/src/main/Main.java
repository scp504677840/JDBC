package main;

import utils.DBUtils;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBUtils.conn();
        System.out.println(conn);
    }
}
