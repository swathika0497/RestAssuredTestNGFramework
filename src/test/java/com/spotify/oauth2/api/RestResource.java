package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Constants.API;
import static com.spotify.oauth2.api.Constants.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
//                header("Authorization", "Bearer " +token).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec()).
                auth().oauth2(token).

//                header("Authorization", "Bearer " +token).
                when().
                get(path).
                then(). spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response put(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " +token).

                when().
                put(path).
                then(). spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String > formParam){
        return given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParam).
                log().all().
        when().
                post(API+TOKEN).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}
