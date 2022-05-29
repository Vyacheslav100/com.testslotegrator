package api;

import core.helpers.GetRandom;
import core.rest.Auth;
import core.rest.Bodies;
import data.CommonData;
import data.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import pojo.GlobalAuthResponse;
import pojo.GlobalPlayerResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest extends Auth {

    @Test
    @Description("Checking the guest auth has status code 200 and the access token got")
    @TmsLink("00001")
    public void guestAuthTest() {
        GlobalAuthResponse guestResponse = guestAuthRequest()
                .then().statusCode(200).log().body()
                .and().extract().body().as(GlobalAuthResponse.class);
        assertThat(guestResponse.getAccess_token().isEmpty())
                .withFailMessage("The token was missed! Check auth service manually!").isFalse();
    }

    @Test
    @Description("Checking the owner auth has status code 200 and the access token got")
    @TmsLink("00001")
    public void ownerAuthTest() {
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
        softAssert.assertThat(ownerResponse.getAccess_token().isEmpty())
                .withFailMessage("The [access_token] was missed! Check auth service manually!")
                .isFalse();
        softAssert.assertThat(ownerResponse.getRefresh_token().isEmpty())
                .withFailMessage("The [refresh_token] was missed! Check auth service manually!")
                .isFalse();
        softAssert.assertAll();
    }
}
