package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.dto.BuysDto;
import my.shop.com.myshopjava.model.Buys;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface BuysService {

    BuysDto create(BuysDto buysDto);

    BuysDto update(BuysDto buysDto, Long buyId);

    void delete(Long buyId);

    BuysDto get(Long buyId);

    Page<Buys> list(HashMap<String, Integer> paginator);

    List<BuysDto> listAll();
}
