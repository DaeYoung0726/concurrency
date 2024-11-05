package concurrency.solutions.redis.Lettuce.product.dto;

import concurrency.solutions.redis.Lettuce.product.entity.Product;

public record ProductRes(
        String name,
        int stockNum
) {
    public static ProductRes from(Product product) {
        return new ProductRes(product.getName(), product.getStockNum());
    }
}
