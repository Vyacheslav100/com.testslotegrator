package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalPlayerResponse {
    public int id;
    public Object country_id;
    public Object timezone_id;
    public String username;
    public String email;
    public String name;
    public String surname;
    public Object gender;
    public Object phone_number;
    public Object birthdate;
    public boolean bonuses_allowed;
    public boolean is_verified;
}
