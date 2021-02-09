package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
