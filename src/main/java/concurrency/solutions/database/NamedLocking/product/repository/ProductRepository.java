package concurrency.solutions.database.NamedLocking.product.repository;

import concurrency.solutions.database.NamedLocking.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("NamedLockingProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {


    /**
     * @param lockName 락을 설정하기 위한 이름.
     * @param timeout 락 획득을 위한 대기 시간
     * @return 성공시 1
     */
    @Query(value = "SELECT GET_LOCK(:lockName, :timeout)", nativeQuery = true)
    int getNamedLock(@Param("lockName") String lockName, @Param("timeout") int timeout);


    /**
     * @param lockName 락을 설정한 이름
     * @return 실패시 -1
     */
    @Query(value = "SELECT RELEASE_LOCK(:lockName)", nativeQuery = true)
    int releaseNamedLock(@Param("lockName") String lockName);
}
