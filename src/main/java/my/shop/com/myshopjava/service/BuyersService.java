package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.dto.BuyersDto;
import my.shop.com.myshopjava.model.Buyers;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface BuyersService{
    BuyersDto create(BuyersDto buyersDto);

    BuyersDto update(BuyersDto buyersDto, Long providerId);

    void delete(Long buyerId);

    BuyersDto get(Long buyerId);

    Page<Buyers> list(HashMap<String, Integer> paginator);

    List<BuyersDto> listAll();
}
