package com.spotify.oauth2.api;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
//                setBaseUri(System.getProperty("BASE_URI"))  - to run on Mulitple environments. Given while running the command in terminal
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).build();


    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
        log(LogDetail.ALL).build();
    }

}
