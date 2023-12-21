package hello.core.beandefinition;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionByAnnotationTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // BeanDefinition을 직접적으로 다루려면 ApplicationContext 보다 AnnotationConfigApplicationContext 를 써야 함
    @Test
    @DisplayName("Annotation 방식의 Bean Definition")
    void findApplicationBean(){
        System.out.println();
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName: " + beanDefinitionName + ", beanDefinition: " + beanDefinition);
            }
        }
    }


}
