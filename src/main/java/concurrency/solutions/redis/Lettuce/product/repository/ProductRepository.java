package concurrency.solutions.redis.Lettuce.product.repository;

import concurrency.solutions.redis.Lettuce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("LettuceProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
