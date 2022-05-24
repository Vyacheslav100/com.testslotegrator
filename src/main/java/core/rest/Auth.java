package core.rest;

import data.CommonData;
import data.Endpoints;
import io.restassured.response.Response;

public class Auth extends BaseReq {

    public Response guestAuthRequest() {
        return sendPostBasicAuth(Endpoints.tokenUrn, CommonData.GUEST_USERNAME, CommonData.GUEST_PASSWORD, Bodies.guestAuthBody(), true);
    }

    public Response ownerAuthRequest(String userName) {
        return sendPostBasicAuth(Endpoints.tokenUrn, CommonData.GUEST_USERNAME, CommonData.GUEST_PASSWORD
                , Bodies.ownerAuthBody(userName), true);
    }
}
