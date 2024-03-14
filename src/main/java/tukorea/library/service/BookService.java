package tukorea.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tukorea.library.domain.Book;
import tukorea.library.domain.Lend;
import tukorea.library.repository.BookRepository;
import tukorea.library.repository.LendRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final LendRepository lendRepository;



    @Autowired
    public BookService(BookRepository bookRepository, LendRepository lendRepository) {
        this.bookRepository = bookRepository;
        this.lendRepository = lendRepository;
    }



    //id로 책 검색
    public Book searchById(Long id) {
        return  bookRepository.findById(id).get();
    }
    //제목으로 책 검색
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContains(title);
    }
    //작가로 책 감섹
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCaseContaining(author);
    }

    //대출
    public void insertLend(Lend lend) {

        lendRepository.save(lend);

    }

    public void updateLend(Long bookId) {
        Lend returnBook = lendRepository.findByBookIdAndReturnDateIsNull(bookId);
        LocalDate now = LocalDate.now();
        System.out.println(returnBook.toString());
        returnBook.setReturnDate(String.valueOf(now));
        lendRepository.save(returnBook);
    }
}
