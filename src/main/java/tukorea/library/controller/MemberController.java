package tukorea.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tukorea.library.DTO.LoginDTO;
import tukorea.library.DTO.LoginReturnDTO;
import tukorea.library.DTO.MemberLendListDTO;
import tukorea.library.domain.Member;
import tukorea.library.jwt.JwtToken;
import tukorea.library.service.MemberService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public String join(@RequestBody Member member) {
        LocalDate now = LocalDate.now();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setCreateDate(String.valueOf(now));
        memberService.join(member);
        return "ok";
    }

    @PostMapping("/members/login")
    public LoginReturnDTO sign(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        return memberService.signIn(username, password);
    }

    @PostMapping("/members/logout")
    public String test(@RequestBody String username) {
        return "ok";
    }

    @GetMapping("/member/info")
    public Member memberInfo(@RequestParam String username) {
        return memberService.getMember(username);
    }

    @GetMapping("/member/duplicate")
    public boolean duplicateCheck(@RequestParam String username) {
        return memberService.checkUsername(username);

    }

    @GetMapping("/member/lend")
    public List<MemberLendListDTO> memberLendBook(@RequestParam String username) {
        return memberService.getMemberLendList(username);
    }

    @DeleteMapping("/member/withdrawal")
    public String memberWithdraw(@RequestParam String username) {
        memberService.deleteMember(username);
        return "ok";
    }

    @PatchMapping("/member/modify")
    public void modifyMemberInfo(@RequestBody Member member) {
        memberService.updateMember(member);
    }
}




