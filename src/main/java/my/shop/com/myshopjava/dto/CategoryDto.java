package my.shop.com.myshopjava.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto extends DTO{

    @NotBlank
    private String name;
}
