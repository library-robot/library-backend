package tukorea.library.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import tukorea.library.domain.Book;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchBookDto {
    private ArrayList<String> imgURLList;
    private Page<Book> books;
}
