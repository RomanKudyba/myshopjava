package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.model.Sells;
import org.springframework.stereotype.Component;

@Component("sellsMapper")
public class SellsMapper {

    public SellsDto toDto(Sells sell){
        SellsDto sellsDto = new SellsDto();
        sellsDto.setCount(sell.getCount());
        sellsDto.setFullPrice(sell.getFullPrice());
        sellsDto.setProduct(sell.getProduct());
        sellsDto.setBuyer(sell.getBuyer());
        return sellsDto;
    }

    public Sells fromDto(SellsDto sellsDto){
        Sells sell = new Sells();
        sell.setCount(sellsDto.getCount());
        sell.setProduct(sellsDto.getProduct());
        sell.setBuyer(sellsDto.getBuyer());
        return sell;
    }
}
