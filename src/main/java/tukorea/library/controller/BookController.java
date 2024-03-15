package tukorea.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tukorea.library.domain.Book;
import tukorea.library.domain.Lend;
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
    public List<Book> getBookList(@RequestParam String keyword) {
        System.out.println(keyword);
        return bookService.searchByTitle(keyword);
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

}
