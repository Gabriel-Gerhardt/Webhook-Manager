package com.project.library.repo.dbConfig;

import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DB {
    static String DB_URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    static final String ROOT_USER = "system";
    static final String ROOT_PASSWORD = "password";

    public Connection dbConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(DB_URL, ROOT_USER, ROOT_PASSWORD);
            Statement stmt = conn.createStatement();
            String grantSQL = "GRANT CONNECT, RESOURCE TO system";
            stmt.executeUpdate(grantSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void createTables(Statement statement) {
        List<String> tableList = new ArrayList<>();
        try {
            tableList.add("CREATE TABLE books " +
                    "(id INTEGER PRIMARY KEY not NULL, " +
                    "title VARCHAR(255) not NULL, " +
                    "author VARCHAR(255) not NULL, " +
                    "publish_year INTEGER)");

            for (String sql : tableList) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createTable(PreparedStatement statement) {
        try{
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
