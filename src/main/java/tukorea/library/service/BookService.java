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
import java.util.Optional;

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
    public boolean insertLend(Lend lend) {
        Book book = bookRepository.findById(lend.getBookId()).get();
        //이미 대출상태인 경우 false
        if(book.getLendStatus()== "T"){
            lendRepository.save(lend);
            return true;
        }
        else return false;
    }

    public boolean updateLend(Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        //이미 반납 상태인 경우 false
        if(book.getLendStatus()== "F"){
            Lend returnBook = lendRepository.findByBookIdAndReturnDateIsNull(bookId);
            LocalDate now = LocalDate.now();
            System.out.println(returnBook.toString());
            returnBook.setReturnDate(String.valueOf(now));
            lendRepository.save(returnBook);
            return true;
        }
        else return false;
    }
}
