package tukorea.library.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tukorea.library.DTO.BookLocatedDTO;
import tukorea.library.domain.Book;
import tukorea.library.domain.DisplacedBook;
import tukorea.library.repository.BookRepository;
import tukorea.library.repository.DisplacedBookRepository;

import java.util.List;


@Service
@Transactional
public class DisplacedBookService {
    DisplacedBookRepository displacedBookRepository;
    BookRepository bookRepository;
    @Autowired
    public DisplacedBookService(DisplacedBookRepository displacedBookRepository, BookRepository bookRepository) {
        this.displacedBookRepository = displacedBookRepository;
        this.bookRepository = bookRepository;
    }



    public void checkBookLocation(BookLocatedDTO bookLocatedDTO) {
        String location = bookLocatedDTO.getLocation();

        for(String rfidTag : bookLocatedDTO.getRfidList()){
            Book book = bookRepository.findByTagNumber(rfidTag);

            if (!book.getCallNumber().substring(0,3).equals(location))
                displacedBookRepository.save(new DisplacedBook(book));
        }
    }

    public List<DisplacedBook> findDisplacedBookList(){
        return displacedBookRepository.findAll();
    }
}