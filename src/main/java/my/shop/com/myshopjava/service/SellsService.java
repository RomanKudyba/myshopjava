package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.model.Sells;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface SellsService {
    SellsDto create(SellsDto sellsDto);

    SellsDto update(SellsDto sellsDto, Long sellsId);

    void delete(Long sellsId);

    SellsDto get(Long sellsId);

    Page<Sells> list(HashMap<String, Integer> paginator);

    List<SellsDto> listAll();
}
