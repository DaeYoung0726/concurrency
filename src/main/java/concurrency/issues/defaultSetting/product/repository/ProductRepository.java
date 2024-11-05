package concurrency.issues.defaultSetting.product.repository;

import concurrency.issues.defaultSetting.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
