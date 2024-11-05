package concurrency.solutions.database.OptimisticLocking.product.repository;

import concurrency.solutions.database.OptimisticLocking.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("OptimisticLockingProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
