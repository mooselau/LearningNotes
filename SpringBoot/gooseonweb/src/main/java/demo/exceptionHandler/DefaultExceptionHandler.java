package demo.exceptionHandler;

import demo.response.JustResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class DefaultExceptionHandler {

    @ExceptionHandler( CustomedException.class )
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public JustResponse handleException(CustomedException exception) {
        log.warn("===> Exception captured!!");
        JustResponse r = new JustResponse("1000", "Exception captured message!!");
        return r;
    }

}
