package concurrency.solutions.javalanguage.reentrantLock.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@Component("reentrantLockProductFacade")
public class ProductFacade {

    @Qualifier("reentrantLockProductService")
    private final ProductService reentrantLockProductService;

    private final ReentrantLock lock = new ReentrantLock();

    public void decrease(Long productId, int quantity) {
        lock.lock();

        try {
            reentrantLockProductService.decreaseStock(productId, quantity);
        } finally {
            lock.unlock();
        }
    }
}