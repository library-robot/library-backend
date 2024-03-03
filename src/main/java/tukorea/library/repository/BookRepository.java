package tukorea.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tukorea.library.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book> findByTitleContains(String title);
    public List<Book> findByAuthorIgnoreCaseContaining(String author);


}
