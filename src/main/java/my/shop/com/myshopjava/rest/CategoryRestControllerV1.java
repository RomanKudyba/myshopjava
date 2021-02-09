package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.CategoryDto;
import my.shop.com.myshopjava.model.Category;
import my.shop.com.myshopjava.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/v1/admin/category/")
public class CategoryRestControllerV1 {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody CategoryDto category) {
        CategoryDto newCategory = categoryService.create(category);
        return ResponseEntity.ok(newCategory);
    }

    @PostMapping("{id}/update")
    public ResponseEntity create(@RequestBody CategoryDto category, @PathVariable("id") Long categoryId) {
        CategoryDto updatedCategory = categoryService.update(category, categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/get")
    public ResponseEntity get(@PathVariable("id") Long categoryId) {
        CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping("list")
    public ResponseEntity list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        HashMap<String, Integer> paginator = new HashMap<String, Integer>();
        paginator.put("page", page);
        paginator.put("pageSize", pageSize);
        Page<Category> categoryDto = categoryService.list(paginator);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("listall")
    public ResponseEntity listAll() {
        List<CategoryDto> categoryDto = categoryService.listAll();
        return ResponseEntity.ok(categoryDto);
    }

}
