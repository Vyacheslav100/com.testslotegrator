package core.rest;

import data.CommonData;
import data.Currencies;
import pojo.GuestAuthRequest;
import pojo.OwnerAuthRequest;
import pojo.RegisterPlayerRequest;

public class Bodies {

    public static GuestAuthRequest guestAuthBody() {
        return GuestAuthRequest.builder()
                .grant_type(CommonData.GUEST_GRANT_TYPE)
                .scope(CommonData.SCOPE)
                .build();
    }

    public static OwnerAuthRequest ownerAuthBody(String userName) {
        return OwnerAuthRequest.builder()
                .grant_type(CommonData.OWNER_GRANT_TYPE)
                .username(userName)
                .password(CommonData.COMMON_PASS)
                .build();
    }

    public static RegisterPlayerRequest registerPlayerBody(String randUserName, String randEmail, String randName, String randSurname) {
        return RegisterPlayerRequest.builder()
                .username(randUserName)
                .password_change(CommonData.COMMON_PASS)
                .password_repeat(CommonData.COMMON_PASS)
                .email(randEmail)
                .name(randName)
                .surname(randSurname)
                .currency_code(Currencies.RUB).build();
    }
}
