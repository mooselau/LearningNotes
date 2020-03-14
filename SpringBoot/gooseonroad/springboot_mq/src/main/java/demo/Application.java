import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(GooseOnWebApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//        UserService svr = context.getBean(UserService.class);
//        svr.printUser(new User());
    }
}
