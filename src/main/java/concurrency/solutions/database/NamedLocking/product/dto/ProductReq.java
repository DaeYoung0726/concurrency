package concurrency.solutions.database.NamedLocking.product.dto;

import concurrency.solutions.database.NamedLocking.product.entity.Product;

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
