package demo.entrypoint;

import demo.controller.SimpleProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SimpleProcessorITCase {

    @Autowired
    private SimpleProcessor processor;

    @LocalServerPort
    private int port;

    @Test
    public void testDoSth() {
        p("Beginning testing..");
        processor.doSth();
        p("Finishing testing..");
    }

    public void p(String msg) {
        System.out.println(msg);
    }

}
