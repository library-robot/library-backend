package tukorea.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Lend {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne @JoinColumn(name = "bookId")
    private Long bookId;
    //    @ManyToOne @JoinColumn(name = "memberId")
    private String username;
    private String lendDate;
    private String returnDate;
}
