package concurrency.solutions.database.NamedLocking.product.dto;

import concurrency.solutions.database.NamedLocking.product.entity.Product;

public record ProductRes(
        String name,
        int stockNum
) {
    public static ProductRes from(Product product) {
        return new ProductRes(product.getName(), product.getStockNum());
    }
}
