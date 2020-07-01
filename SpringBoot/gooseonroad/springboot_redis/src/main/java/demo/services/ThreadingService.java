package demo.services;

import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThreadingService {

    @Autowired
    private RedisCommonService redisService;

    private final Thread[] threads = new Thread[100];
    private CountDownLatch latch = new CountDownLatch(100);

    public ThreadingService() {
    }

    public void triggerThreadingBusiness() {
        for (Thread t : threads) {
            t = new Thread(() -> {
                try {
                    latch.countDown();
                    redisService.businessLogic("9999");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
