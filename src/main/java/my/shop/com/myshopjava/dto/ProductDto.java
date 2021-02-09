package my.shop.com.myshopjava.dto;

import lombok.Data;
import my.shop.com.myshopjava.model.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private Category category;

    private String name;

    @NotNull(message = "price can not be null")
    private Double price;
}
