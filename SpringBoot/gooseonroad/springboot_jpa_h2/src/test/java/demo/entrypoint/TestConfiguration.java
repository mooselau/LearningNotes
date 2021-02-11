package demo.entrypoint;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        return new ServletRegistrationBean(new WebServlet(), "/h2-console/*");
    }
}
