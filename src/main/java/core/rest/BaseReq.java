package core.rest;

import data.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseReq {

    RequestSpecification request = given();

    public Response sendGetOAuth(String urn, String token) {
        request.baseUri(Endpoints.baseUri);
        request.log().uri();
        request.auth().preemptive().oauth2(token);
        request.log().headers();
        return request.get(urn);
    }

    public Response sendPostBasicAuth(String urn, String basicName, String basicPass, Object body, Boolean isBodyNeed) {
        request.baseUri(Endpoints.baseUri);
        request.log().uri();
        request.auth().preemptive().basic(basicName, basicPass);
        request.log().headers();
        if (isBodyNeed) {
            request.contentType(ContentType.JSON);
            request.body(body);
            request.log().body();
        }
        return request.post(urn);
    }

    public Response sendPostOAuth(String urn, String token, Object body, Boolean isBodyNeed) {
        request.baseUri(Endpoints.baseUri);
        request.log().uri();
        request.auth().preemptive().oauth2(token);
        request.log().headers();
        if (isBodyNeed) {
            request.contentType(ContentType.JSON);
            request.body(body);
            request.log().body();
        }
        return request.post(urn);
    }
}
