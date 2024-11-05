package concurrency.solutions.javalanguage.sync.product.service;

import concurrency.solutions.javalanguage.sync.product.dto.ProductReq;
import concurrency.solutions.javalanguage.sync.product.dto.ProductRes;
import concurrency.solutions.javalanguage.sync.product.entity.Product;
import concurrency.solutions.javalanguage.sync.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("synchronizedProductService")
public class ProductService {

    @Qualifier("synchronizedProductRepository")
    private final ProductRepository synchronizedProductRepository;

    @Transactional
    public Long createStock(ProductReq productReq) {

        return synchronizedProductRepository.save(productReq.toEntity()).getId();
    }

    /* 동시성 문제 발생하는 구역 */
    @Transactional
    public void decreaseStock(Long productId, int quantity) {

        Product product = synchronizedProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값"));

        // 동시성 문제가 발생할 수 있는 부분: 여러 스레드가 동시에 접근할 때
        int currentStock = product.getStockNum();

        product.updateStockNum(currentStock - quantity);
    }

    @Transactional(readOnly = true)
    public ProductRes getProduct(Long productId) {

        Product product = synchronizedProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값"));

        return ProductRes.from(product);
    }
}
