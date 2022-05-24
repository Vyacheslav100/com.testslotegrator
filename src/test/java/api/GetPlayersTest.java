package api;

import core.helpers.GetRandom;
import core.rest.Auth;
import core.rest.Bodies;
import data.CommonData;
import data.Endpoints;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import pojo.GlobalAuthResponse;
import pojo.GlobalPlayerResponse;

public class GetPlayersTest extends Auth {

    @Test
    public void getSinglePlayerTest() {
        SoftAssertions softAssert = new SoftAssertions();
        String randomName = GetRandom.randomString(1).toUpperCase() + GetRandom.randomString(4).toLowerCase();
        String randomSurname = GetRandom.randomString(1).toUpperCase() + GetRandom.randomString(6).toLowerCase();
        String randomUserName = randomName.toLowerCase() + randomSurname.toLowerCase();
        String randomEmail = randomName.toLowerCase() + randomSurname.toLowerCase() + CommonData.COMMON_EMAIL;

        GlobalAuthResponse guestResponse = guestAuthRequest()
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalAuthResponse.class);

        GlobalPlayerResponse registerPlayerResponse = sendPostOAuth(Endpoints.playersUrn, guestResponse.getAccess_token()
                , Bodies.registerPlayerBody(randomUserName, randomEmail, randomName, randomSurname), true)
                .then().statusCode(201).log().body()
                .and().extract().body().as(GlobalPlayerResponse.class);

        GlobalAuthResponse ownerResponse = ownerAuthRequest(registerPlayerResponse.username)
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalAuthResponse.class);

        GlobalPlayerResponse getSinglePlayerResponse = sendGetOAuth(Endpoints.playersUrn + "/" + registerPlayerResponse.id,
                ownerResponse.getAccess_token())
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalPlayerResponse.class);

        softAssert.assertThat(registerPlayerResponse.id).isEqualTo(getSinglePlayerResponse.id);
        softAssert.assertThat(getSinglePlayerResponse.country_id).isNull();
        softAssert.assertThat(getSinglePlayerResponse.timezone_id).isNull();
        softAssert.assertThat(randomUserName).isEqualTo(getSinglePlayerResponse.username);
        softAssert.assertThat(randomEmail).isEqualTo(getSinglePlayerResponse.email);
        softAssert.assertThat(randomName).isEqualTo(getSinglePlayerResponse.name);
        softAssert.assertThat(randomSurname).isEqualTo(getSinglePlayerResponse.surname);
        softAssert.assertThat(getSinglePlayerResponse.gender).isNull();
        softAssert.assertThat(getSinglePlayerResponse.phone_number).isNull();
        softAssert.assertThat(getSinglePlayerResponse.birthdate).isNull();
        softAssert.assertThat(getSinglePlayerResponse.bonuses_allowed).isTrue();
        softAssert.assertThat(getSinglePlayerResponse.is_verified).isFalse();
        softAssert.assertAll();
    }

    @Test
    public void getSomeSinglePlayerTest() {
        SoftAssertions softAssert = new SoftAssertions();
        String randomName = GetRandom.randomString(1).toUpperCase() + GetRandom.randomString(4).toLowerCase();
        String randomSurname = GetRandom.randomString(1).toUpperCase() + GetRandom.randomString(6).toLowerCase();
        String randomUserName = randomName.toLowerCase() + randomSurname.toLowerCase();
        String randomEmail = randomName.toLowerCase() + randomSurname.toLowerCase() + CommonData.COMMON_EMAIL;

        GlobalAuthResponse guestResponse = guestAuthRequest()
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalAuthResponse.class);

        GlobalPlayerResponse registerPlayerResponse = sendPostOAuth(Endpoints.playersUrn, guestResponse.getAccess_token()
                , Bodies.registerPlayerBody(randomUserName, randomEmail, randomName, randomSurname), true)
                .then().statusCode(201).log().body()
                .and().extract().body().as(GlobalPlayerResponse.class);

        GlobalAuthResponse ownerResponse = ownerAuthRequest(registerPlayerResponse.username)
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalAuthResponse.class);

        sendGetOAuth(Endpoints.playersUrn + "/" + CommonData.SOME_PLAYER, ownerResponse.getAccess_token())
                .then().statusCode(404).log().body();
    }
}
