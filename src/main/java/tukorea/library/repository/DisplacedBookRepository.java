package tukorea.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tukorea.library.domain.DisplacedBook;


@Repository
public interface DisplacedBookRepository extends JpaRepository<DisplacedBook,Long> {



}
