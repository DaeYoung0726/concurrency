package concurrency.solutions.javalanguage.sync.product.dto;

import concurrency.solutions.javalanguage.sync.product.entity.Product;

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
