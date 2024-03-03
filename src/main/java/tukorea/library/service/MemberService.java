package tukorea.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tukorea.library.DTO.MemberLendListDTO;
import tukorea.library.domain.Member;
import tukorea.library.jwt.JwtToken;
import tukorea.library.jwt.JwtTokenProvider;
import tukorea.library.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public void deleteMember(String username) {
        memberRepository.deleteByUsername(username);
    }

    public void updateMember(Member member) {
        if (member.getPassword().length() <60){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        }
         memberRepository.save(member);
    }

    @PersistenceContext
    private EntityManager em;
    public List<MemberLendListDTO> getMemberLendList(String username) {
        return em.createQuery(
                        "SELECT new tukorea.library.DTO.MemberLendListDTO(b.title, l.lendDate, l.returnDate) " +
                                "FROM Book b, Lend l " +
                                "WHERE b.id = l.bookId AND l.username = :username", MemberLendListDTO.class)
                .setParameter("username", username)
                .getResultList();
    }
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member getMember(String  username) {
        return memberRepository.findByUsername(username).get();
    }

    public boolean checkUsername(String username) {
        return memberRepository.findByUsername(username).isEmpty();
    }

    @Transactional(readOnly = true)
    public void ValidateDuplicateMember(Member member) {
//        List<Member> findMembers = memberRepository.findByName(member.getName());
//        if (!findMembers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
    }

    @Transactional
    public JwtToken signIn(String username, String password) {
        try {
            // 1. username + password 를 기반으로 Authentication 객체 생성
            // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
            // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
            return jwtToken;
        } catch (Exception e) {
            return JwtToken.builder().build();
        }

    }



}
