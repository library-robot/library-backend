package tukorea.library.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tukorea.library.DTO.BookLocatedDTO;
import tukorea.library.DTO.LocatedInfo;
import tukorea.library.domain.Book;
import tukorea.library.domain.DisplacedBook;
import tukorea.library.repository.BookRepository;
import tukorea.library.repository.DisplacedBookRepository;

import java.util.ArrayList;
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



    public List<LocatedInfo> checkBookLocation(BookLocatedDTO bookLocatedDTO) {
        ArrayList<LocatedInfo> locatedInfoList = new ArrayList<>();
        String location = bookLocatedDTO.getLocation();
        for(String rfidTag : bookLocatedDTO.getRfidList()){
            Book book = bookRepository.findByTagNumber(rfidTag);
            if (!book.getCallNumber().substring(0,3).equals(location)){
                //나중에 쿼리 튜닝 해야됨. 너무 쿼리 많음
                if(displacedBookRepository.findByIsbn(book.getISBN()).isEmpty()){
                    displacedBookRepository.save(new DisplacedBook(book));
                    LocatedInfo locatedInfo = new LocatedInfo(book.getId(),book.getCallNumber());
                    locatedInfoList.add(locatedInfo);
                }

            }
        }
        return locatedInfoList;
    }

    public List<DisplacedBook> findDisplacedBookList(){
        return displacedBookRepository.findAll();
    }
}
