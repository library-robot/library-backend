package tukorea.library.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tukorea.library.jwt.JwtToken;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginReturnDTO {
    JwtToken jwtToken;
    String role;

}
