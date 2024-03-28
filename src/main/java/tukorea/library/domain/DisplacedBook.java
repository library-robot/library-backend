package tukorea.library.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class DisplacedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String callNumber;
    private String publisher;

    public DisplacedBook() {
    }

    public DisplacedBook(Book book) {
        this.id = book.getId();
        this.isbn = book.getISBN();
        this.title = book.getTitle();
        this.callNumber = book.getCallNumber();
        this.publisher = book.getPublisher();
    }
}
