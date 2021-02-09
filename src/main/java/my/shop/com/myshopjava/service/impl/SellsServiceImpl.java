package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.helpers.PaginatorHelper;
import my.shop.com.myshopjava.model.Products;
import my.shop.com.myshopjava.model.Sells;
import my.shop.com.myshopjava.repository.ProductsRepository;
import my.shop.com.myshopjava.repository.SellsRepository;
import my.shop.com.myshopjava.service.SellsService;
import my.shop.com.myshopjava.utils.SellsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SellsServiceImpl implements SellsService {

    private final SellsRepository sellsRepository;

    private final SellsMapper sellsMapper;

    private final ProductsRepository productsRepository;

    @Autowired
    SellsServiceImpl(
            SellsRepository sellsRepository,
            SellsMapper sellsMapper,
            ProductsRepository productsRepository){
        this.sellsRepository = sellsRepository;
        this.sellsMapper = sellsMapper;
        this.productsRepository = productsRepository;
    }

    @Override
    public SellsDto create(SellsDto sellsDto) {
        Sells sell = sellsMapper.fromDto(sellsDto);
        Products product = productsRepository.findById(sell.getProduct().getId()).orElseThrow();
        sell.setFullPrice(product.getPrice() * sell.getCount());
        Sells newSell = sellsRepository.save(sell);
        return sellsMapper.toDto(newSell);
    }

    @Override
    public SellsDto update(SellsDto sellsDto, Long sellId) {
        try {
            Sells sell = sellsRepository.findById(sellId).orElseThrow(EntityNotFoundException::new);
            sell.setCount(sellsDto.getCount());
            sell.setProduct(sellsDto.getProduct());
            sell.setBuyer(sellsDto.getBuyer());
            sell.setFullPrice(sellsDto.getProduct().getPrice() * sellsDto.getCount());

            Sells updatedSell = sellsRepository.save(sell);
            return sellsMapper.toDto(updatedSell);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("sell with id "+sellId+" not found");
        }
    }

    @Override
    public void delete(Long sellId) {
        try {
            Sells sell = sellsRepository.findById(sellId).orElseThrow(EntityNotFoundException::new);
            sellsRepository.delete(sell);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("sell with id "+sellId+" not found");
        }
    }

    @Override
    public SellsDto get(Long sellId) {
        try {
            Sells sell = sellsRepository.findById(sellId).orElseThrow(EntityNotFoundException::new);
            return sellsMapper.toDto(sell);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("sell with id "+sellId+" not found");
        }
    }

    @Override
    public Page<Sells> list(HashMap<String, Integer> paginator) {
        Page<Sells> allSells = sellsRepository.findAll(PaginatorHelper.getPageable(paginator));
        return allSells;
    }

    @Override
    public List<SellsDto> listAll() {
        List<Sells> sells = sellsRepository.findAll(Sort.by("id"));
        List<SellsDto> sellsDtoList = new ArrayList<>();
        sells.forEach(buyer -> {
            sellsDtoList.add(sellsMapper.toDto(buyer));
        });
        return sellsDtoList;
    }
}
