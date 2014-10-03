package com.nasimeshomal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        // if exists : connects
        // if does not exist : create and connect
        Connection connection= DriverManager.getConnection("jdbc:sqlite:test.db");

        //Connection connection= DriverManager.getConnection("jdbc:sqlite:C:/test.db");


        // memory database
        //Connection connection= DriverManager.getConnection("jdbc:sqlite::memory:");

        System.out.println("");
    }
}
