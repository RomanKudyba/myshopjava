package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.CategoryDto;
import my.shop.com.myshopjava.model.Category;
import org.springframework.stereotype.Component;

@Component("categoryMapper")
public class CategoryMapper{

    public CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public Category fromDto(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }
}
