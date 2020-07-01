package demo.services;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisCommonService {

    @Autowired
    private RedisTemplate redisTemplate;

    private int usageCounter;

    public void businessLogic(String key) throws Exception {
        tp("start processing..");

        long currentTime = System.currentTimeMillis();
        boolean locking = redisTemplate.opsForValue()
                .setIfAbsent(key, String.valueOf(currentTime), 60, TimeUnit.SECONDS);
        if (locking) {
            tp("locking status: " + locking);
            tp("resource locked, doing business..");
            // doing business
            Thread.sleep(1000L);
            tp("end processing.." + ", usage: " + (++usageCounter));
        } else {
            tp("locking status: " + locking);
            tp("Resource locked, please try again later");
        }
    }

    public boolean checkCache(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (value == null) {
            return false;
        }
        long cacheTime = Long.parseLong(value);
        long currentTime = System.currentTimeMillis();
        // assume 5 seconds check window
        if (cacheTime + 5 * 1000L < currentTime) {
            // not expired
            return true;
        } else {
            // expired
            return false;
        }
    }

    public void tp(String msg) {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "\t" + msg);
    }

    public void p(String msg) {
        System.out.println(msg);
    }

}
