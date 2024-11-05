package concurrency.solutions.javalanguage.reentrantLock.product.repository;

import concurrency.solutions.javalanguage.reentrantLock.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("reentrantLockProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
