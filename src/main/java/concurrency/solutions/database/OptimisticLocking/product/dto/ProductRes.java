package concurrency.solutions.database.OptimisticLocking.product.dto;

import concurrency.solutions.database.OptimisticLocking.product.entity.Product;

public record ProductRes(
        String name,
        int stockNum
) {
    public static ProductRes from(Product product) {
        return new ProductRes(product.getName(), product.getStockNum());
    }
}
