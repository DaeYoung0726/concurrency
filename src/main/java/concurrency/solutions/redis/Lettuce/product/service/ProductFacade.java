package concurrency.solutions.redis.Lettuce.product.service;

import concurrency.solutions.redis.Lettuce.product.repository.RedisLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("LettuceProductFacade")
public class ProductFacade {

    private final RedisLockRepository redisLockRepository;
    @Qualifier("LettuceProductService")
    private final ProductService LettuceProductService;

    public void decrease(Long key, int quantity) throws InterruptedException {
        // 락을 최대 5초 동안 시도하고, 락을 3초 동안 유지
        boolean isLocked = redisLockRepository.tryLock(key, 5000, 3000);
        if (!isLocked) {
            throw new IllegalStateException("락 획득 실패. (대기 시간 지남 등이 원인)");
        }
        //lock 획득 성공시
        try{
            LettuceProductService.decreaseStock(key, quantity);
        }finally {
            //락 해제
            redisLockRepository.unlock(key);
        }
    }
}
