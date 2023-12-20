package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FixedDiscountPolicyTest {

    @Test
    public void discount_success() {
        // given
        Member member = new Member(1L,"Kim", Grade.VIP);
        int price = 10000;

        DiscountPolicy fixedDiscountPolicy = new FixedDiscountPolicy();
        // when
        int discountedPrice = fixedDiscountPolicy.discount(member, price);

        // then
        Assertions.assertThat(discountedPrice).isEqualTo(1000);
    }

}