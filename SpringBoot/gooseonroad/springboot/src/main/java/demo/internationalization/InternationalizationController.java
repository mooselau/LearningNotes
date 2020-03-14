package demo.internationalization;

import demo.response.JustResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/i18n")
public class InternationalizationController {

    @Autowired
    private I18nDefaultService service;

    @GetMapping("/greeting")
    public JustResponse<String> getGreetingMessage(Locale locale) {
        String msg = service.getGreetingMessage(locale);
        return new JustResponse<>(msg);
    }

}
