package com.project.library.db;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DB {
    static String DB_URL = "jdbc:oracle:thin:@localhost:1521/freepdb1";
    static final String ROOT_USER = "system";
    static final String ROOT_PASSWORD = "password";

    public Connection dbConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(DB_URL, ROOT_USER, ROOT_PASSWORD);
        try (Statement stmt = conn.createStatement()) {
            String grantSQL = "GRANT CONNECT, RESOURCE TO system";
            stmt.executeUpdate(grantSQL);
        }

        return conn;
    }

    public static void createTables(Statement statement) throws SQLException {
        List<String> tableList = new ArrayList<>();
        tableList.add("CREATE TABLE books " +
                "(id INTEGER PRIMARY KEY not NULL, " +
                "title VARCHAR(255) not NULL, " +
                "author VARCHAR(255) not NULL, " +
                "publish_year INTEGER)" );

        for(String sql : tableList) {
            statement.executeUpdate(sql);
        }
    }
}
