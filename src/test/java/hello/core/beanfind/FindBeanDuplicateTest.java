package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindBeanDuplicateTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeansConfig.class);

    // 빈타입 중복 예외 처리
    @Test
    @DisplayName("중복 타입 빈 예외처리")
    void findBeanDuplicate_x(){
        assertThrows(NoUniqueBeanDefinitionException.class ,() -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("중복 타입 빈 한개 이름으로 찾기")
    void findBeanDuplicateByName(){
        MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);
        System.out.println("bean = " + bean);
    }

    @Test
    @DisplayName("중복 타입 빈 다 찾기")
    void findBeanDuplicateAll(){

        // 해당 타입의 빈들을 모두 맵 형태로 반환
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        // 빈들을 출력
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key)) ;
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }





    // 내부 정적 클래스 선언 - 테스트 클래스의 스코프 내에서만 적용하기 위함
    static class BeansConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
