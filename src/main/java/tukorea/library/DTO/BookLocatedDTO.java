package tukorea.library.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookLocatedDTO {
    private String[] rfidList;
    private String location;

}
