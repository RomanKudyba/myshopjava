package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.Buys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BuysRepository extends JpaRepository<Buys, Long> {
    ArrayList<Buys> findByProductId(Long productId);
}
