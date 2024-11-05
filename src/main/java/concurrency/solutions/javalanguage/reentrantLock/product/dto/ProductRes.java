package concurrency.solutions.javalanguage.reentrantLock.product.dto;

import concurrency.solutions.javalanguage.reentrantLock.product.entity.Product;

public record ProductRes(
        String name,
        int stockNum
) {
    public static ProductRes from(Product product) {
        return new ProductRes(product.getName(), product.getStockNum());
    }
}
