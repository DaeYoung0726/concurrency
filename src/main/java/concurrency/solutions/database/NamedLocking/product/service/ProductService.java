package concurrency.solutions.database.NamedLocking.product.service;

import concurrency.solutions.database.NamedLocking.product.dto.ProductReq;
import concurrency.solutions.database.NamedLocking.product.dto.ProductRes;
import concurrency.solutions.database.NamedLocking.product.entity.Product;
import concurrency.solutions.database.NamedLocking.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("NamedLockingProductService")
public class ProductService {

    @Qualifier("NamedLockingProductRepository")
    private final ProductRepository NamedLockingProductRepository;

    @Transactional
    public Long createStock(ProductReq productReq) {

        return NamedLockingProductRepository.save(productReq.toEntity()).getId();
    }

    /* 동시성 문제 발생하는 구역 */
    @Transactional
    public void decreaseStock(Long productId, int quantity) {

        Product product = NamedLockingProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값"));

        // 동시성 문제가 발생할 수 있는 부분: 여러 스레드가 동시에 접근할 때
        int currentStock = product.getStockNum();

        product.updateStockNum(currentStock - quantity);

    }

    @Transactional(readOnly = true)
    public ProductRes getProduct(Long productId) {

        Product product = NamedLockingProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값"));

        return ProductRes.from(product);
    }
}
