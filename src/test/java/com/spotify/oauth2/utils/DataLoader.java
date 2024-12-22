package com.spotify.oauth2.utils;

import java.util.Properties;

// to load the data in data.properties
public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if (dataLoader==null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getPlaylistId(){
        String prop = properties.getProperty("get_playlist_id");
        if(prop!= null){
            return prop;
        }
        else throw new RuntimeException(" playlist id is not specified in data.properties");
    }
    public String getUpdatePlaylistId(){
        String prop = properties.getProperty("get_update_playlist_id");
        if(prop!= null){
            return prop;
        }
        else throw new RuntimeException(" update playlist id is not specified in data.properties");
    }
}
