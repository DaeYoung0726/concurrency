package concurrency.issues.defalutSetting;

import concurrency.issues.defaultSetting.product.dto.ProductReq;
import concurrency.issues.defaultSetting.product.dto.ProductRes;
import concurrency.issues.defaultSetting.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Long productId;

    @BeforeEach
    void setUp() {

        productId = productService.createStock(new ProductReq("상품", 100));
    }

    @Test
    void 동시성_문제_발생_테스트() throws InterruptedException {
        // 스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(5); // 스레드 5개 지정

        for (int i = 0; i < 100; i++) {

            executorService.submit(() -> {
                try {
                    productService.decreaseStock(productId, 1);
                } catch (Exception e) {
                    System.out.println("에러 발생 - " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

        ProductRes productRes = productService.getProduct(productId);
        assertEquals(0, productRes.stockNum());

    }
}