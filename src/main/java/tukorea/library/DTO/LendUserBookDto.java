package tukorea.library.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LendUserBookDto {
    private String userRfidNumber;
    private String bookRfidNum;
}
