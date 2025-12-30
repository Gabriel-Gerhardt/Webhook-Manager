package com.project.library;

import com.project.library.repo.db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws SQLException {
        DB.dbConnection();
        SpringApplication.run(LibraryApplication.class,args);
    }
}
