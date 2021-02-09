package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.dto.CategoryDto;
import my.shop.com.myshopjava.model.Category;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface CategoryService extends BaseService{
    CategoryDto create(CategoryDto category);

    CategoryDto update(CategoryDto category, Long categoryId);

    void delete(Long categoryId);

    CategoryDto get(Long categoryId);

    Page<Category> list(HashMap<String, Integer> paginator);

    List<CategoryDto> listAll();
}
