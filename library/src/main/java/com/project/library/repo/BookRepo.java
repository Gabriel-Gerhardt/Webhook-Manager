package com.project.library.repo;

import com.project.library.entities.Book;
import com.project.library.repo.dbConfig.DB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepo {

    private final Connection conn;

    public BookRepo(DB db)  {
        this.conn = db.dbConnection();
    }

    public void insertBook (Book book) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO books " +
                    "(id, title, author, publish_year) " +
                    "VALUES " +
                    "(?, ?, ?, ?)"
            );
            st.setLong(1, book.getId());
            st.setString(2, book.getTitle());
            st.setString(3, book.getAuthorName());
            st.setInt(4, book.getPublishYear());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertBookList (List<Book> listBook) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO books (id, title, author, publish_year) VALUES (?, ?, ?, ?)");
            for (Book book : listBook) {
                st.setLong(1, book.getId());
                st.setString(2, book.getTitle());
                st.setString(3, book.getAuthorName());
                st.setInt(4, book.getPublishYear());
                st.addBatch();
            }
            st.executeBatch();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> findAll()  {
        List<Book> lista = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"));
                lista.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    public Book findById (long id)  {
        Book book = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    public void updateById (Book book, long id) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE books " +
                    "SET title = ?, author = ?, publish_year = ? " +
                    "WHERE " +
                    "(id = ?)"
            );
            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthorName());
            st.setInt(3, book.getPublishYear());
            st.setLong(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById (long id)  {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM books " +
                    "WHERE id = ?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
