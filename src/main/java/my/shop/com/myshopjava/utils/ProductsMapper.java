package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.ProductDto;
import my.shop.com.myshopjava.model.Products;
import org.springframework.stereotype.Component;

@Component("productsMapper")
public class ProductsMapper{

    public ProductDto toDto(Products product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        return productDto;
    }

    public Products fromDto(ProductDto productDto){
        Products product = new Products();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        return product;
    }
}
