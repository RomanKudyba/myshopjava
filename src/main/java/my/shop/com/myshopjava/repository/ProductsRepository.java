package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.Category;
import my.shop.com.myshopjava.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Category findByName(String name);

    Page<Products> findAll(Pageable pageable);
}
