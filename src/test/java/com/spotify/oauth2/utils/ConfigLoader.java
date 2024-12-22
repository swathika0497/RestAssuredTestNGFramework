package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
    private  final Properties properties;
    private static ConfigLoader configLoader;  // creating a variable that represents object of this class

    private ConfigLoader(){ // private Constructor , we should not be able to create a new instance of this class outside this class. we should not be able to access this constructot outside this class
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }
    public static ConfigLoader getInstance(){ // ensures only one object of this class is created
        if (configLoader==null){
            configLoader=new ConfigLoader(); // create a configloader if it is null
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("Client ID is not specified in config.properties");
    }
    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("Client secret is not specified in config.properties");
    }
    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("refresh_token is not specified in config.properties");
    }
    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("grant_type is not specified in config.properties");
    }
    public String getUserId(){
        String prop = properties.getProperty("user_id");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("user id is not specified in config.properties");
    }


}
