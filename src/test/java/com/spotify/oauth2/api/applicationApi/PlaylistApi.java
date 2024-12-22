package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Constants.PLAYLIST;
import static com.spotify.oauth2.api.Constants.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class PlaylistApi {
    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLIST,getToken(),requestPlaylist);
          }
    public static Response post(String accessToken, Playlist requestPlaylist){
        return RestResource.post(USERS+"/" +ConfigLoader.getInstance().getUserId()+PLAYLIST,accessToken,requestPlaylist);


    }

    public static Response get(String playlistId){
        return RestResource.get(PLAYLIST +"/"+playlistId ,getToken());


    }

    public static Response updatePlaylist(String playlistId,Playlist requestPlaylist){
        return RestResource.put(PLAYLIST+"/"+playlistId,getToken(),requestPlaylist);


    }
}
