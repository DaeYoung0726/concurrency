package concurrency.solutions.javalanguage.reentrantLock.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "reentrantLock_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stockNum;

    @Builder
    public Product(String name, int stockNum) {
        this.name = name;
        this.stockNum = stockNum;
    }

    public void updateStockNum(int stockNum) {
        this.stockNum = stockNum;
    }
}
