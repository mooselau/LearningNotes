package demo.exceptionHandler;

import org.springframework.stereotype.Service;

@Service
public class DefaultService {

    public String normalMethod() {
        String result = "this is result.";
        // during processing..
        return result;
    }

    public void exceptionMethod() {
        // during processing..
        throw new CustomedException("1000", "This is Exception threw by method..");
    }

}
