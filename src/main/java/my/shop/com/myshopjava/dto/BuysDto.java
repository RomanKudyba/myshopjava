package my.shop.com.myshopjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import my.shop.com.myshopjava.model.Products;
import my.shop.com.myshopjava.model.Providers;

@Data
public class BuysDto extends DTO{

    private Integer count;

    @JsonProperty("full_price")
    private Double fullPrice;
    //product_id
    //@todo
    private Products product;

    private Providers provider;

}
