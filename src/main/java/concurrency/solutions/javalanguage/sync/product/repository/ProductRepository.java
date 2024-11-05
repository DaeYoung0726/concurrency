package concurrency.solutions.javalanguage.sync.product.repository;

import concurrency.solutions.javalanguage.sync.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("synchronizedProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
