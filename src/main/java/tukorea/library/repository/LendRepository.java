package tukorea.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tukorea.library.domain.Lend;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Lend findByBookIdAndReturnDateIsNull(Long bookId);
}
