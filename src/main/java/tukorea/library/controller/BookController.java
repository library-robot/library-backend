package tukorea.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tukorea.library.DTO.BookLocatedDTO;
import tukorea.library.DTO.SearchBookDto;
import tukorea.library.domain.Book;
import tukorea.library.domain.DisplacedBook;
import tukorea.library.domain.Lend;
import tukorea.library.openAPI.BookAPI;
import tukorea.library.openAPI.BookDetail;
import tukorea.library.service.BookService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search-book")
    public SearchBookDto getBookList(@RequestParam String keyword, Pageable pageable) {
        System.out.println(keyword);
        return bookService.searchByTitle(keyword, pageable);
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam Long id) {
        System.out.println("test\n");
        return bookService.searchById(id);
    }

    @PostMapping("/book/lend")
    public boolean lendBook(@RequestBody Lend lend) {
        LocalDate now = LocalDate.now();
        lend.setLendDate(String.valueOf(now));
        return bookService.insertLend(lend);
    }

    @PostMapping("/book/return")
    public boolean returnBook(@RequestBody Lend lend) {

        return bookService.updateLend(lend.getBookId());
    }

    @GetMapping("/book/detail")
    public BookDetail detailBook(@RequestParam String isbn) {
        BookAPI bookAPI = new BookAPI();
        BookDetail bookDetail = new BookDetail();
        try {
            bookDetail = bookAPI.bookDetail(isbn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookDetail;
    }





}
