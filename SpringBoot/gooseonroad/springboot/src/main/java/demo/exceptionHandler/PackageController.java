package demo.exceptionHandler;

import demo.response.JustResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that triggers events under this package.
 */
@RestController
@RequestMapping("/exception-handler")
public class PackageController {

    @Autowired
    private DefaultService service;

    @GetMapping("/normal")
    public JustResponse<String> getNormalResponse() {
        String ret = service.normalMethod();
        return new JustResponse<>(ret);
    }

    @GetMapping("/exception")
    public JustResponse<String> getErrorResponse() {
        service.exceptionMethod();
        return null;
    }

}
