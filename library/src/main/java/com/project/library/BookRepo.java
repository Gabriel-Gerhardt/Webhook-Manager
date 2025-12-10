package com.project.library;

import com.project.library.db.DB;
import com.project.library.entities.Book;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepo {

    private final Connection conn;
    private final String url = "http://localhost:8100/notification";
    private HttpClient client = HttpClient.newHttpClient();
    public BookRepo(DB db) throws SQLException {
        this.conn = db.dbConnection();
    }

    public void CreateBook(Book book) throws SQLException {
        PreparedStatement st;
        long id = book.getId();
        if(FindById(id) != null){
            return;
        }
        String title = book.getTitle();
        String author = book.getAuthor_name();
        int publish_year = book.getPublish_year();
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
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertBookList(List<Book> listBook) throws SQLException{
        PreparedStatement st;
        for(Book book : listBook) {
            long id = book.getId();
            if(FindById(id) != null){
                return;
            }
            String title = book.getTitle();
            String author = book.getAuthor_name();
            int publish_year = book.getPublish_year();
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
    public List<Book> FindAll() throws SQLException {
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

    private Book FindById(long id) throws SQLException{
        PreparedStatement st;
        ResultSet rs = null;
        Book book = null;
        st = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
        st.setLong(1,id);

        rs = st.executeQuery();

        if(rs.next()) {
            do {
                book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"));
            } while(rs.next());
        }
        return book;
    }
    public void UpdateById(Book book, long id) throws SQLException {
        PreparedStatement st;
        String title = book.getTitle();
        String author = book.getAuthor_name();
        int publish_year = book.getPublish_year();
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
    public void DeleteById(long id) throws SQLException{
        PreparedStatement st;
        st = conn.prepareStatement("DELETE FROM books "+
                "WHERE id = ?");
        st.setLong(1,id);
        st.executeUpdate();
    }
}
