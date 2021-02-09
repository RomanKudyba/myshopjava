package my.shop.com.myshopjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import my.shop.com.myshopjava.model.Buyers;
import my.shop.com.myshopjava.model.Products;

import javax.validation.constraints.NotBlank;

@Data
public class SellsDto extends DTO{

    private Integer count;

    @JsonProperty("full_price")
    private Double fullPrice;

    private Products product;

    private Buyers buyer;
}
