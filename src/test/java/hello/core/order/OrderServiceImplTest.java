package hello.core.order;

import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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
        Assertions.assertThat(result.getDiscount()).isEqualTo(9000);

    }

}