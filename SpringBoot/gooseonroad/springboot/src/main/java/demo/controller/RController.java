package demo.controller;

import demo.properties.UserProperties;
import demo.response.JustResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RController {

    @Autowired
    private UserProperties userProperties;

    @GetMapping("/property-name")
    public JustResponse printPropertyName() {
        String ret = userProperties.getPropertyName();
        return new JustResponse(ret);
    }

    @GetMapping("/property-num")
    public JustResponse printPropertyNum() {
        Integer ret = userProperties.getPropertyNum();
        return new JustResponse(ret);
    }
}
