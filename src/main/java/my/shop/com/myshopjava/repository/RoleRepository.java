package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
