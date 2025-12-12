package com.project.library.webhook;

import com.project.library.db.DB;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlRepo {

    private final Connection conn;
    private final String url = "http://localhost:8100/notification";
    private HttpClient client = HttpClient.newHttpClient();
    public UrlRepo(DB db) throws SQLException {
        this.conn = db.dbConnection();
    }

    public void registerUrl(String incomingUrl, String requestUrl) throws SQLException {
        Map<String, String> map = new HashMap<>();
        map.put(incomingUrl, requestUrl);
        UrlMapper urlMapper = new UrlMapper(map);
        PreparedStatement st;

        st = conn.prepareStatement("INSERT INTO webhooks " +
                "(target_url, incoming_url) " +
                "VALUES " +
                "(?, ?)"
        );
        st.setString(2, incomingUrl);
        st.setString(1, requestUrl);
        st.executeUpdate();
    }

}
