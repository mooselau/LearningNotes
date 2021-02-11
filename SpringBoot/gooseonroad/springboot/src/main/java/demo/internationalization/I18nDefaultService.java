package demo.internationalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class I18nDefaultService {

    @Autowired
    private MessageSource messageSource;

    public String getGreetingMessage(Locale locale) {
        return messageSource.getMessage("greeting",null, locale);
    }

}
