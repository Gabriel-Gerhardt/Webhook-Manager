package com.project.library.webhook;

import java.util.HashMap;
import java.util.Map;

public class UrlMapper {
    public Map<String, String> urlMap = new HashMap<>();

    public UrlMapper(){
    }
    public UrlMapper(Map<String,String> urlMap){
        this.urlMap = urlMap;
    }

}
