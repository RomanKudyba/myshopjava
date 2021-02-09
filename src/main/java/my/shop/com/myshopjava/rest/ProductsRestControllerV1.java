package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.ProductDto;
import my.shop.com.myshopjava.model.Products;
import my.shop.com.myshopjava.service.ProductsService;
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
@RequestMapping(value = "/api/v1/admin/product/")
public class ProductsRestControllerV1 {

    @Autowired
    private ProductsService productsService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody ProductDto productDto){
        ProductDto newProduct = productsService.create(productDto);
        return ResponseEntity.ok(newProduct);
    }

    @PostMapping("{id}/update")
    public ResponseEntity update(@RequestBody ProductDto productDto, @PathVariable("id") Long productId) {
        ProductDto updatedProduct = productsService.update(productDto, productId);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long productId) {
        productsService.delete(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/get")
    public ResponseEntity get(@PathVariable("id") Long productId) {
        ProductDto productDto = productsService.get(productId);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("list")
    public ResponseEntity list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        HashMap<String, Integer> paginator = new HashMap<String, Integer>();
        paginator.put("page", page);
        paginator.put("pageSize", pageSize);
        Page<Products> products = productsService.list(paginator);
        return ResponseEntity.ok(products);
    }

    @GetMapping("listall")
    public ResponseEntity listAll() {
        List<ProductDto> productDto = productsService.listAll();
        return ResponseEntity.ok(productDto);
    }

}
