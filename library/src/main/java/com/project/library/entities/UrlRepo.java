package com.project.library.entities;

import com.project.library.repo.db.DB;
import com.project.library.view.webhook.UrlMapper;
import org.springframework.stereotype.Repository;

import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlRepo {

    private final Connection conn;
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
    public void getUrl(String requestUrl) throws SQLException {
    PreparedStatement st = conn.prepareStatement("SELECT incoming_url FROM webhooks "+
            "WHERE target_url = "
                    +"(?)"
            );
    //st.setString(incomingUrl);
    }

    public String event(Object o){
        return o.toString();
    }

}
