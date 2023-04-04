package com.onlinemarket.member;

import com.onlinemarket.web.MemberForm;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MemberController {
  private final MemberRepository memberRepository;

  // 회원가입 폼 이동
  @GetMapping("/members/new")
  public String joinForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());
    return "member/joinMemberForm";
  }

  // 회원가입
  @PostMapping("/member/new")
  public String joinMember(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());
    member.setEmail(form.getEmail());
    member.setPhone(form.getPhone());
    member.setPassword(form.getPassword());
    memberRepository.save(member);
    return "redirect:/";
  }

  // 회원 목록 조회
  @GetMapping("/members")
  public String findAllMember(Model model) {
    List<Member> members = memberRepository.findAll();
    model.addAttribute("members", members);
    return "members/memberList";
  }

  // 회원 삭제 요청, 폼으로 이동 후 비밀번호 확인
  @GetMapping("/member/delete")
  public String deleteForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());
    return "member/deleteForm";
  }

  @PostMapping("/member/{memberId}/delete")
  public String deleteMember(@PathVariable Long memberId, MemberForm form) {
    Optional<Member> member = memberRepository.findById(memberId);
    if (form.getPassword().equals(member.get().getPassword())) {
      memberRepository.deleteById(member.get().getId());
    }
    return "redirect:/";
  }

  // 회원 정보 수정
  @GetMapping("/member/{memberId}/update")
  public String updateForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());
    return "member/updateForm";
  }

  @PostMapping("/member/{memberId}/update")
  public String updateMember(@PathVariable Long memberId, MemberForm form) {
    Optional<Member> member = memberRepository.findById(memberId);
    member.get().setPhone(form.getPhone());
    member.get().setPassword(form.getPassword());
    memberRepository.save(member.get());
    return "redirect:/";
  }

  // 이메일로 아이디 찾기
  @GetMapping("/member/find")
  public String findForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());
    return "member/findForm";
  }

  // 이메일 인증 추가 후 이메일 인증 시 회원정보 수정폼으로 이동
  @PostMapping("/member/{memberId}/find")
  public String findMemberByEmail(
      @PathVariable String memeberEmail, RedirectAttributes redirectAttributes) {
    Member member1 = memberRepository.findMemberByEmail(memeberEmail);
    redirectAttributes.addAttribute("memberId", member1.getId());
    return "redirect:/member/{memberId}/update";
  }
}
