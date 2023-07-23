package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : 사용자A 10000원주문
         int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : 사용자B 20000원주문
        int userBPrice =statefulService2.order("userB", 20000);
        //ThreadA : 사용자A 주문금액 조회
        System.out.println(userAPrice);
        System.out.println(userBPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}