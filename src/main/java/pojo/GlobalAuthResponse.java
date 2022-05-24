package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalAuthResponse {

    String token_type;
    int expires_in;
    String access_token;
    String refresh_token;
}
