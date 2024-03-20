package tukorea.library.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "book2")
public class Book {
    //generatedvalue는 db가 자동생성해줄때 사용.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ISBN;
    private String title;
    private String author;
    private String callNumber;
    private String genre;
    private String publicationYear;
    private String lendStatus;
    //    private String authorSmbl;
    private String page;
    private String edition;
    private String language;
    private String publisher;

}
