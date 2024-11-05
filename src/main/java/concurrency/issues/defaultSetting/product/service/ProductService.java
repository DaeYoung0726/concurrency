package concurrency.issues.defaultSetting.product.service;

import concurrency.issues.defaultSetting.product.dto.ProductReq;
import concurrency.issues.defaultSetting.product.dto.ProductRes;
import concurrency.issues.defaultSetting.product.entity.Product;
import concurrency.issues.defaultSetting.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("issueProductService")
public class ProductService {

    @Qualifier("issueProductRepository")
    private final ProductRepository issueProductRepository;

    @Transactional
    public Long createStock(ProductReq productReq) {

        return issueProductRepository.save(productReq.toEntity()).getId();
    }

    /**
     * 동시성 문제가 발생하는 코드
     * 여러 개의 스레드가 한 번에 접근한다면, 같은 currentStock를 얻어 동시성 문제 발생
     */
    @Transactional
    public void decreaseStock(Long productId, int quantity) {

        Product product = issueProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값"));

        // 동시성 문제가 발생할 수 있는 부분: 여러 스레드가 동시에 접근할 때
        int currentStock = product.getStockNum();

        product.updateStockNum(currentStock - quantity);
    }


    @Transactional(readOnly = true)
    public ProductRes getProduct(Long productId) {

        Product product = issueProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값"));

        return ProductRes.from(product);
    }
}
