package logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class LoggerDemo {

    Logger log = LoggerFactory.getLogger("HelloLogsene");

    public static void main(String[] args) {
        LoggerDemo demo = new LoggerDemo();
        demo.entrypoint();
    }

    private void entrypoint() {
        log.info("This is one log trace.");
        warnFuntion();
        try {
            logicFunction();
        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }

    private void warnFuntion() {
        log.warn("This is a warning trace.");
    }

    private void logicFunction() {
        log.info("Doing something..");
        try {
            errorFunction();
        } catch (Exception e) {
            log.error("something bad happened", e);
            throw new IllegalArgumentException("just illegal argument", e);
        }
    }

    private void errorFunction() {
        throw new RuntimeException("Something unexpected happened..");
    }
}
