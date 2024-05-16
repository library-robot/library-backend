package tukorea.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tukorea.library.domain.DisplacedBook;
import tukorea.library.domain.Member;

import java.util.Optional;


@Repository
public interface DisplacedBookRepository extends JpaRepository<DisplacedBook,Long> {
    Optional<DisplacedBook> findByIsbn(String isbn);


}
