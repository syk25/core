package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price - discountFixAmount;
        } else {
            return 0;
        }
    }
}