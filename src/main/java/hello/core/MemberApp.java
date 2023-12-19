package hello.core;

import hello.core.member.*;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"kim",Grade.VIP);
        memberService.join(member);
        Member result = memberService.findMember(member.getId());
        System.out.println("new member : " + member.getId());
        System.out.println("find member : " + result.getId());
    }
}
