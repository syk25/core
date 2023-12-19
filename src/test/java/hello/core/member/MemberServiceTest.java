package hello.core.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    @Test
    public void join() {
        // given
        Member member = new Member(1L,"Kim",Grade.BASIC);
        MemberService memberService = new MemberServiceImpl();
        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        // then
        assertThat(findMember).isEqualTo(member);
    }

}