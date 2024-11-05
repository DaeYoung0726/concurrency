package concurrency.solutions.redis.Redisson.product.repository;

import concurrency.solutions.redis.Redisson.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RedissonProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
