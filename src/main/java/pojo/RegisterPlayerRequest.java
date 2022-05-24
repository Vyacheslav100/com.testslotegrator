package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPlayerRequest {
    public String username;
    public String password_change;
    public String password_repeat;
    public String email;
    public String name;
    public String surname;
    public String currency_code;
}
