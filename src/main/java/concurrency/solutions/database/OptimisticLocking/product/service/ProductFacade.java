package concurrency.solutions.database.OptimisticLocking.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("OptimisticLockingProductFacade")
public class ProductFacade {

    @Qualifier("OptimisticLockingProductService")
    private final ProductService OptimisticLockingProductService;

    public void decrease(Long productId, int quantity) {
        try {
            OptimisticLockingProductService.decreaseStock(productId, quantity);
        } catch (OptimisticLockingFailureException e) {
            decrease(productId, quantity);
        }
    }
}
