package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import demo.services.RedisCommonService;
import demo.services.ThreadingService;


@RestController
@RequestMapping("/redis")
public class AppController {

    @Autowired
    private ThreadingService threadingService;

    @Autowired
    private RedisCommonService redisService;

    @GetMapping("/demo")
    public String testDemo() {
        return "Hello, there!";
    }

    @GetMapping("/")
    public void testRedisQuery() {
        threadingService.triggerThreadingBusiness();
    }

    @GetMapping("/{key}")
    public boolean testValidateCache(@PathVariable("key") String key) {
        return redisService.checkCache(key);
    }

}
