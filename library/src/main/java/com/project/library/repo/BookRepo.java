package com.project.library.repo;

import com.project.library.db.DB;
import com.project.library.entities.Book;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepo {

    private final Connection conn;
    private final String url = "http://localhost:8100/notification";
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    public BookRepo(DB db) throws SQLException {
        this.conn = db.dbConnection();
    }

    public void insertBook (Book book) throws SQLException {
        PreparedStatement st;
        long id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthorName();
        int publish_year = book.getPublishYear();
        st = conn.prepareStatement("INSERT INTO books " +
                "(id, title, author, publish_year) " +
                "VALUES " +
                "(?, ?, ?, ?)"
                );
        st.setLong(1,id);
        st.setString(2,title);
        st.setString(3,author);
        st.setInt(4,publish_year);
        st.executeUpdate();
    }

    public void insertBookList (List<Book> listBook) throws SQLException{
        PreparedStatement st;
        for(Book book : listBook) {
            long id = book.getId();
            String title = book.getTitle();
            String author = book.getAuthorName();
            int publish_year = book.getPublishYear();
            st = conn.prepareStatement("INSERT INTO books " +
                    "(id, title, author, publish_year) " +
                    "VALUES " +
                    "(?, ?, ?, ?)"
            );
            st.setLong(1, id);
            st.setString(2, title);
            st.setString(3, author);
            st.setInt(4, publish_year);
            st.executeUpdate();
        }
    }
    public List<Book> findAll() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Book> lista = new ArrayList<>();
        st = conn.prepareStatement("SELECT * FROM books");
        rs = st.executeQuery();
        if (rs.next()) {
            while (true) {
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"));
                lista.add(book);
                if (!rs.next()) {
                    break;
                }
            }
        }
        return lista;

    }

    public Book findById (long id) throws SQLException{
        PreparedStatement st;
        ResultSet rs = null;
        Book book = null;
        st = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
        st.setLong(1,id);
        rs = st.executeQuery();
        if(rs.next()) {
                book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"));
        }
        return book;
    }
    public void updateById (Book book, long id) throws SQLException {
        PreparedStatement st;
        String title = book.getTitle();
        String author = book.getAuthorName();
        int publish_year = book.getPublishYear();
        st = conn.prepareStatement("UPDATE books " +
                "SET title = ?, author = ?, publish_year = ? " +
                "WHERE " +
                "(id = ?)"
        );
        st.setString(1,title);
        st.setString(2,author);
        st.setInt(3,publish_year);
        st.setLong(4, id);
        st.executeUpdate();
    }
    public void deleteById (long id) throws SQLException{
        PreparedStatement st;
        st = conn.prepareStatement("DELETE FROM books "+
                "WHERE id = ?");
        st.setLong(1,id);
        st.executeUpdate();
    }
}
