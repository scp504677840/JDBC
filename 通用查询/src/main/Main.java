package main;

import bean.Book;
import exception.DBUtilsException;
import utils.DBUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //String sql = "select id,name,price from book where id = ?";
        String sql = "select * from book";
        try {
            //List<Book> values = DBUtils.query(Book.class, sql, 5);
            List<Book> values = DBUtils.query(Book.class, sql);
            values.forEach(System.out::println);
        } catch (DBUtilsException e) {
            e.printStackTrace();
        }

    }
}
