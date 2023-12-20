package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        /* 1. 기존 설정정보 주석처리
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/

        //2. 스프링에 의한 관리 도입
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L,"kim",Grade.VIP);
        memberService.join(member);

        Member result = memberService.findMember(member.getId());
        System.out.println("new member : " + member.getId());
        System.out.println("find member : " + result.getId());
    }
}
