package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    public void createOrder_success() {
        // given

        memberService.join(new Member(1L, "Kim", Grade.VIP));
        Long memberId = memberService.findMember(1L).getId();
        String itemName = "item";
        int itemPrice = 10000;

        // when
        Order result = orderService.createOrder(memberId, itemName, itemPrice);

        // then
        Assertions.assertThat(result.getDiscount()).isEqualTo(1000);

    }

}