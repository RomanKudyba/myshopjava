package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.dto.ProductDto;
import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.model.Products;
import my.shop.com.myshopjava.model.Sells;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

public interface ProductsService{
    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto, Long productId);

    void delete(Long productId);

    ProductDto get(Long productId);

    Page<Products> list(HashMap<String, Integer> paginator);

    List<ProductDto> listAll();

    SellsDto buy(Long productId, String username, Sells sell) throws Exception;
}
