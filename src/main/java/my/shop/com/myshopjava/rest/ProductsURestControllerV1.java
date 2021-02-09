package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.helpers.AuthHelper;
import my.shop.com.myshopjava.model.Sells;
import my.shop.com.myshopjava.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users/product/")
@Validated
public class ProductsURestControllerV1 {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/{id}/buy")
    public ResponseEntity buy(
            @Valid @RequestBody Sells sell,
            @PathVariable("id") Long productId) throws Exception {
        String username = AuthHelper.authedUsername();
        SellsDto newSell = productsService.buy(productId, username, sell);
        return ResponseEntity.ok(newSell);
    }
}
