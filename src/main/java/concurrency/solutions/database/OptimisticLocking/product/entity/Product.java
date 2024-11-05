package concurrency.solutions.database.OptimisticLocking.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "optimistic_locking_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stockNum;

    @Version
    private Long version;

    @Builder
    public Product(String name, int stockNum) {
        this.name = name;
        this.stockNum = stockNum;
    }

    public void updateStockNum(int stockNum) {
        this.stockNum = stockNum;
    }
}
