package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.Buyers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyersRepository extends JpaRepository<Buyers, Long> {
//    Buyers findByFirstname(String username);
    Buyers findByFirstName(String username);
}
