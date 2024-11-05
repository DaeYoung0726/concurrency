package concurrency.solutions.redis.Lettuce.product.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisLockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    /* 락 획득 대기시간 있는 버전 */
    public Boolean tryLock(Long key, long waitTimeMillis, long lockTimeMillis) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < waitTimeMillis) {
            Boolean isLocked = redisTemplate
                    .opsForValue()
                    .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(lockTimeMillis));
            if (Boolean.TRUE.equals(isLocked)) {
                return true; // 락 획득 성공
            }
            // 락을 얻지 못했으므로 대기 후 재시도
            Thread.sleep(100);
        }
        return false; // 락 획득 실패
    }

    /* 락 획득 대기시간 없는 버전 */
    public Boolean lock(Long key) throws InterruptedException {
        Boolean isLocked = redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000));

        if (!Boolean.TRUE.equals(isLocked)) {
            Thread.sleep(100); // 락을 얻지 못했을 때 대기 시간 추가
        }
        return isLocked;
    }

    public Boolean unlock(Long key) {
        return redisTemplate.delete(generateKey(key));
    }

    private String generateKey(Long key) {
        return key.toString();
    }
}
