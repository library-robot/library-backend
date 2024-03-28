package tukorea.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tukorea.library.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findByTitleContains(String title , Pageable pageable);
    List<Book> findByAuthorIgnoreCaseContaining(String author);
    Book findByTagNumber(String rfidTag);



}
