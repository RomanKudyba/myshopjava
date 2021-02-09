package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.BuyersDto;
import my.shop.com.myshopjava.model.Buyers;
import org.springframework.stereotype.Component;

@Component("buyersMapper")
public class BuyersMapper {

    public BuyersDto toDto(Buyers buyer){
        BuyersDto buyersDto = new BuyersDto();
        buyersDto.setFirstName(buyer.getFirstName());
        buyersDto.setMidName(buyer.getMidName());
        buyersDto.setSurname(buyer.getSurname());
        buyersDto.setAge(buyer.getAge());
        buyersDto.setEmail(buyer.getEmail());
        buyersDto.setPhone(buyer.getPhone());

        return buyersDto;
    }

    public Buyers fromDto(BuyersDto buyersDto){
        Buyers buyer = new Buyers();
        buyer.setFirstName(buyersDto.getFirstName());
        buyer.setMidName(buyersDto.getMidName());
        buyer.setSurname(buyersDto.getSurname());
        buyer.setAge(buyersDto.getAge());
        buyer.setEmail(buyersDto.getEmail());
        buyer.setPhone(buyersDto.getPhone());

        return buyer;
    }
}
