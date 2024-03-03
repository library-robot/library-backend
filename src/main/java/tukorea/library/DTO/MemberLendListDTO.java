package tukorea.library.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLendListDTO {
    private String title;

    public MemberLendListDTO(String title, String lendDate, String returnDate) {
        this.title = title;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    private String lendDate;
    private String returnDate;
}
