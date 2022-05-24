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

public class RegTest extends Auth {

    @Test
    public void regPlayerTest() {
        SoftAssertions softAssert = new SoftAssertions();
        String randomName = GetRandom.randomString(1).toUpperCase() + GetRandom.randomString(4).toLowerCase();
        String randomSurname = GetRandom.randomString(1).toUpperCase() + GetRandom.randomString(6).toLowerCase();
        String randomUserName = randomName.toLowerCase() + randomSurname.toLowerCase();
        String randomEmail = randomName.toLowerCase() + randomSurname.toLowerCase() + CommonData.COMMON_EMAIL;

        GlobalAuthResponse basicAuthResponse = guestAuthRequest()
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalAuthResponse.class);
        GlobalPlayerResponse registerPlayerResponse = sendPostOAuth(Endpoints.playersUrn, basicAuthResponse.getAccess_token()
                , Bodies.registerPlayerBody(randomUserName, randomEmail, randomName, randomSurname), true).then().statusCode(201).log().body()
                .and().extract().body().as(GlobalPlayerResponse.class);

        //TODO: Get DB access to check id
        softAssert.assertThat(registerPlayerResponse.country_id).isNull();
        softAssert.assertThat(registerPlayerResponse.timezone_id).isNull();
        softAssert.assertThat(randomUserName).isEqualTo(registerPlayerResponse.username);
        softAssert.assertThat(randomEmail).isEqualTo(registerPlayerResponse.email);
        softAssert.assertThat(randomName).isEqualTo(registerPlayerResponse.name);
        softAssert.assertThat(randomSurname).isEqualTo(registerPlayerResponse.surname);
        softAssert.assertThat(registerPlayerResponse.gender).isNull();
        softAssert.assertThat(registerPlayerResponse.phone_number).isNull();
        softAssert.assertThat(registerPlayerResponse.birthdate).isNull();
        softAssert.assertThat(registerPlayerResponse.bonuses_allowed).isTrue();
        softAssert.assertThat(registerPlayerResponse.is_verified).isFalse();
        softAssert.assertAll();
    }
}
