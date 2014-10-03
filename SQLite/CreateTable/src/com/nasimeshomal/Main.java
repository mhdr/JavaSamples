package com.nasimeshomal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection= DriverManager.getConnection("jdbc:sqlite:test.db");

        Statement command=connection.createStatement();
        String sql="CREATE TABLE \"People\" (\n" +
                "\"PersonId\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"FirstName\"  TEXT NOT NULL,\n" +
                "\"LastName\"  TEXT NOT NULL\n" +
                ");";

        command.execute(sql);
        command.close();
        connection.close();
    }
}
