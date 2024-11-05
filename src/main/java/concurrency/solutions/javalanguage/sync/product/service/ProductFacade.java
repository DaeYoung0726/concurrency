package concurrency.solutions.javalanguage.sync.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("synchronizedProductFacade")
public class ProductFacade {

    @Qualifier("synchronizedProductService")
    private final ProductService synchronizedProductService;

    public synchronized void decrease(Long productId, int quantity) {
        synchronizedProductService.decreaseStock(productId, quantity);
    }
}