package tukorea.library.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocatedInfo {
    private Long id;
    private String callNumber;

    public LocatedInfo(Long id, String callNumber) {
        this.id = id;
        this.callNumber = callNumber;
    }
}
