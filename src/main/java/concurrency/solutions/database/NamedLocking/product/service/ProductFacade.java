package concurrency.solutions.database.NamedLocking.product.service;

import concurrency.solutions.database.NamedLocking.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("NamedLockingProductFacade")
public class ProductFacade {

    @Qualifier("NamedLockingProductService")
    private final ProductService NamedLockingProductService;

    @Qualifier("NamedLockingProductRepository")
    private final ProductRepository NamedLockingProductRepository;

    public void decrease(Long productId, int quantity) {
        String lockName = "lock::" + productId;
        int timeout = 5;
        try {
            int available = NamedLockingProductRepository.getNamedLock(lockName, timeout);
            if (available == 1) {
                NamedLockingProductService.decreaseStock(productId, quantity);
            } else {
                throw new IllegalArgumentException("락 획득 실패");
            }
        } finally {
            int release = NamedLockingProductRepository.releaseNamedLock(lockName);
            if (release == -1) {
                throw new IllegalArgumentException("락 반납 실패");
            }
        }
    }
}
