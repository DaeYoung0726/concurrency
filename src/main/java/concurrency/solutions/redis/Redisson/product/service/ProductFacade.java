package concurrency.solutions.redis.Redisson.product.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component("RedissonProductFacade")
public class ProductFacade {

    @Qualifier("RedissonProductService")
    private final ProductService RedissonProductService;
    private final RedissonClient redissonClient;

    private static final String LOCK = "lock:";
    private static final long WAIT_TIME = 5L;
    private static final long LEASE_TIME = 5L;

    public void decrease(Long key, int quantity) throws InterruptedException {


        RLock lock = redissonClient.getLock(LOCK + key);

        try {
            boolean available = lock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS);
            if (!available) {
                throw new IllegalStateException("락 획득 실패. (대기 시간 지남 등이 원인)");
            }

            RedissonProductService.decreaseStock(key, quantity);
        } finally {
            lock.unlock();
        }
    }
}
