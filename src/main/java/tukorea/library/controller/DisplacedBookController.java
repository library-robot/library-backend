package tukorea.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tukorea.library.DTO.BookLocatedDTO;
import tukorea.library.domain.DisplacedBook;
import tukorea.library.service.DisplacedBookService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DisplacedBookController {
    DisplacedBookService displacedBookService;
    @Autowired
    public DisplacedBookController(DisplacedBookService displacedBookService) {
        this.displacedBookService = displacedBookService;
    }

    @GetMapping("/book/displaced-book")
    public List<DisplacedBook> displacedBookList(){
        return displacedBookService.findDisplacedBookList();
    }
    @PostMapping("/book/located-book")
    public void isLocatedBook(@RequestBody BookLocatedDTO bookLocatedDTO) {
        System.out.println(bookLocatedDTO.toString());
        displacedBookService.checkBookLocation(bookLocatedDTO);
//        return null;
    }
}
