package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    Page<Category> findAll(Pageable pageable);
}
