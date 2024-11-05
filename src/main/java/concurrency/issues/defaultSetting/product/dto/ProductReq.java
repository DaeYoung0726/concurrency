package concurrency.issues.defaultSetting.product.dto;

import concurrency.issues.defaultSetting.product.entity.Product;

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
