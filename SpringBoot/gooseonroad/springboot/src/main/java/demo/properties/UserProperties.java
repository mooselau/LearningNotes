package demo.properties;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProperties {

    @Value("${custom.property.name:}")
    private String propertyName;

    @Value("${custom.property.num:18}")
    private Integer propertyNum;

    @Value("${PROJECTS_HOME}")
    private String envVar;

    @PostConstruct
    public void init() {
        System.out.println("=> propertyNum: " + propertyNum);
        System.out.println("=> envVar: " + envVar);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Integer getPropertyNum() {
        return propertyNum;
    }
}
