package concurrency.solutions.javalanguage.sync.product.dto;

import concurrency.solutions.javalanguage.sync.product.entity.Product;

public record ProductRes(
        String name,
        int stockNum
) {
    public static ProductRes from(Product product) {
        return new ProductRes(product.getName(), product.getStockNum());
    }
}
