package concurrency.solutions.javalanguage.reentrantLock.product.dto;

import concurrency.solutions.javalanguage.reentrantLock.product.entity.Product;

public record ProductReq(
        String name,
        int stockNum
) {

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .stockNum(stockNum)
                .build();
    }
}
