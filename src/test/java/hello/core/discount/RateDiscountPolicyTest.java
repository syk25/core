package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName(("VIP 는 10% 할인 정책 적용"))
    public void vip_o() {
        // given
        Member member = new Member(1L,"Kim", Grade.VIP);
        int price = 10000;
        // when
        int discountedPrice = discountPolicy.discount(member,price);
        // then
        Assertions.assertThat(discountedPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닐 경우, 할인 정책 적용을 배제")
    public void vip_x() {
        Member member = new Member(1L,"Kim", Grade.BASIC);
        int price = 10000;
        // when
        int discountedPrice = discountPolicy.discount(member,price);
        // then
        Assertions.assertThat(discountedPrice).isEqualTo(0);
    }

}