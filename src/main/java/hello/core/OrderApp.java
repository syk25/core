package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Member member = new Member(1L,"Customer_VIP", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberService.findMember(member.getId()).getId(), "itemA", 10000);

        System.out.println(order.toString());

    }
}
