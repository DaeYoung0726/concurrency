package concurrency.solutions.database.PessimisticLocking.product.repository;

import concurrency.solutions.database.PessimisticLocking.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PessimisticLockingProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * PESSIMISTIC_WRITE: 수정(쓰기)만 대기. 읽기는 허용
     * PESSIMISTIC_READ: 읽기와 수정 모두 대기
     * PESSIMISTIC_FORCE_INCREMENT: 레코드를 읽거나 수정할 때 잠금을 거는 것 외에도, 버전 필드의 값을 강제로 증가.
     * 데이터가 읽히는 순간에도 해당 엔티티에 대한 버전 충돌 가능성을 제어
     */
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("select p from pessimistic_locking_product p where p.id = :productId")
    Optional<Product> findByIdWithPLock(@Param("productId") Long productId);
}
