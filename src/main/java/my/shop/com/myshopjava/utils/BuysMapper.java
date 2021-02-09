package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.BuysDto;
import my.shop.com.myshopjava.model.Buys;
import org.springframework.stereotype.Component;

@Component("buysMapper")
public class BuysMapper {

    public BuysDto toDto(Buys buy){
        BuysDto buysDto = new BuysDto();
        buysDto.setCount(buy.getCount());
        buysDto.setFullPrice(buy.getFullPrice());
        buysDto.setProduct(buy.getProduct());
        buysDto.setProvider(buy.getProvider());
        return buysDto;
    }

    public Buys fromDto(BuysDto buysDto){
        Buys buy = new Buys();
        buy.setCount(buysDto.getCount());
        buy.setProduct(buysDto.getProduct());
        buy.setProvider(buysDto.getProvider());
        return buy;
    }
}
