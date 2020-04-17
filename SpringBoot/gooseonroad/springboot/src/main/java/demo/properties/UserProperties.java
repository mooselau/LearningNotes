package demo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProperties {

    @Value("${custom.property.name:defaultName}")
    private String propertyName;

    @Value("${custom.property.num:18}")
    private Integer propertyNum;

    public String getPropertyName() {
        return propertyName;
    }

    public Integer getPropertyNum() {
        return propertyNum;
    }
}
