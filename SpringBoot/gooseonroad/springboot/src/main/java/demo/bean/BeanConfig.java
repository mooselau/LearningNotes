package demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public JustBean justBean() {
        String name = "First bean";
        return new JustBean(name);
    }

}
