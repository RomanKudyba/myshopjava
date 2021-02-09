package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.CategoryDto;
import my.shop.com.myshopjava.helpers.PaginatorHelper;
import my.shop.com.myshopjava.model.Category;
import my.shop.com.myshopjava.repository.CategoryRepository;
import my.shop.com.myshopjava.service.CategoryService;
import my.shop.com.myshopjava.utils.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category newCategory = categoryRepository.save(categoryMapper.fromDto(categoryDto));
        return categoryMapper.toDto(newCategory);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Long categoryId){
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
            category.setName(categoryDto.getName());
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toDto(updatedCategory);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("category with id "+categoryId+" not found");
        }
    }

    @Override
    public void delete(Long categoryId) {
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
            categoryRepository.delete(category);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("category with id "+categoryId+" not found");
        }
    }

    @Override
    public CategoryDto get(Long categoryId) {
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
            return categoryMapper.toDto(category);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("category with id "+categoryId+" not found");
        }
    }

    @Override
    public Page<Category> list(HashMap<String, Integer> paginator) {
        Page<Category> allCategories = categoryRepository.findAll(PaginatorHelper.getPageable(paginator));
        return allCategories;
    }

    @Override
    public  List<CategoryDto> listAll() {
        List<Category> categories = categoryRepository.findAll(Sort.by("id"));
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categories.forEach(category -> {
            categoryDtoList.add(categoryMapper.toDto(category));
        });
        return categoryDtoList;
    }
}
