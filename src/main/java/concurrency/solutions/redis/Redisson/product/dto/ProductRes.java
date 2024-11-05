package concurrency.solutions.redis.Redisson.product.dto;

import concurrency.solutions.redis.Redisson.product.entity.Product;

public record ProductRes(
        String name,
        int stockNum
) {
    public static ProductRes from(Product product) {
        return new ProductRes(product.getName(), product.getStockNum());
    }
}
