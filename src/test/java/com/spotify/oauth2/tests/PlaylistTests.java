package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseTest{

    public Playlist playlistBuilder(String name, String description, boolean _public){

        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public).build();
    }
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
    }
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode ){
        assertThat(actualStatusCode,equalTo(statusCode.getCode()));
    }

    @Test
    public void createPlaylist(){

        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false );

        Response response= PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

    }

    @Test
    public void getPlaylist(){
        Playlist requestPlaylist = playlistBuilder("Updated Playlist","Updated playlist description", true);

        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);

        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void updatePlaylist(){

        Playlist requestPlaylist = playlistBuilder("New Playlist","New playlist description", false);

        Response response = PlaylistApi.updatePlaylist(DataLoader.getInstance().getUpdatePlaylistId(),requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithName(){
        Playlist requestPlaylist = playlistBuilder("New Playlist","New playlist description", false);

        Response response = PlaylistApi.post("12345",requestPlaylist);
               assertStatusCode(response.statusCode(),StatusCode.CODE_400);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);


    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken(){
        Playlist requestPlaylist = playlistBuilder("New Playlist","New playlist description", false);


        Response response = PlaylistApi.post("1234",requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);


        Error error = response.as(Error.class);

           assertThat(error.getError().getStatus(),equalTo(401));
      assertThat(error.getError().getMessage(),equalTo(StatusCode.CODE_401.getMsg()));




    }



}
