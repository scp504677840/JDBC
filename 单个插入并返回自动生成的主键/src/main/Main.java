package main;

import exception.DBUtilsException;
import utils.DBUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        String sql = "insert into book (name,price) values(?,?)";
        try {
            BigInteger key = DBUtils.insert(sql, "javascript", new BigDecimal(22.22));
            System.out.println("key: " + key);
        } catch (DBUtilsException e) {
            e.printStackTrace();
        }
    }
}
