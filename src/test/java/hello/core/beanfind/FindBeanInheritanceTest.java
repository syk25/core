package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindBeanInheritanceTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanInheritanceConfig.class);


    // 예외처리
    @Test
    @DisplayName("부모 타입으로 조회시 자식 타입까지 조회 됨 -> 중복 + 예외처리")
    void parentChildDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    // 자식이 둘 이상인 경우 빈 이름 지정하기 : 부모타입, 자식이름으로 조회 -> 자식 클래스 타입으로 검증
    @Test
    @DisplayName("자식이름을 지정해서 빈 가져오기")
    void getChildSingle(){
        DiscountPolicy fixedDiscountPolicy = ac.getBean("fixedDiscountPolicy", DiscountPolicy.class);
        assertThat(fixedDiscountPolicy).isInstanceOf(FixedDiscountPolicy.class);
    }

    @Test
    @DisplayName("하위 타입으로 자식 타입 조회")
    void getChildByType(){
        RateDiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기") // 자식 빈까지 모두 조회하게 됨
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", object : " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    // 주의! 출력물로 시스템 통과여부를 하는 것은 좋지 않음

    // Object 타입을 이용해서 모든 빈 조회하기
    @Test
    @DisplayName("Object 타입으로 모두 조회하기") // 자식 빈까지 모두 조회하게 됨
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", object : " + beansOfType.get(key));
        }
    }


    @Configuration
    static class BeanInheritanceConfig {
        @Bean
        DiscountPolicy fixedDiscountPolicy(){// 역할과 구현을 구분하기 위해 타입은 역할로 지정
            return new FixedDiscountPolicy();
        }
        @Bean
        DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
    }
}
