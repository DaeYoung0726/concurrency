package concurrency.solutions.database.PessimisticLocking.product.dto;

import concurrency.solutions.database.PessimisticLocking.product.entity.Product;

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
