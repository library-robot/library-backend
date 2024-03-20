package tukorea.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tukorea.library.DTO.SearchBookDto;
import tukorea.library.domain.Book;
import tukorea.library.domain.Lend;
import tukorea.library.openAPI.BookAPI;
import tukorea.library.openAPI.BookDetail;
import tukorea.library.repository.BookRepository;
import tukorea.library.repository.LendRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    public SearchBookDto searchByTitle(String title , Pageable pageable) {
        Page<Book> books = bookRepository.findByTitleContains(title, pageable);
        Iterator<Book> iterator = books.getContent().iterator();

        ArrayList<String> imgURLList = new ArrayList<>();
        BookAPI bookAPI = new BookAPI();

        while (iterator.hasNext()){
            try {
                BookDetail bookDetail = bookAPI.bookDetail(iterator.next().getISBN());
                if(bookDetail != null)
                    imgURLList.add(bookDetail.getImage());
                else
                    imgURLList.add("null");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        SearchBookDto searchBookDto = new SearchBookDto();
        searchBookDto.setBooks(books);
        searchBookDto.setImgURLList(imgURLList);
        return searchBookDto;
    }
    //작가로 책 감섹
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCaseContaining(author);
    }

    //대출
    public boolean insertLend(Lend lend) {
        Book book = bookRepository.findById(lend.getBookId()).get();
        //이미 대출상태인 경우 false
        if(book.getLendStatus().equals("T")){
            lendRepository.save(lend);
            return true;
        }
        else return false;
    }

    public boolean updateLend(Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        //이미 반납 상태인 경우 false
        if(book.getLendStatus().equals("F")){
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
