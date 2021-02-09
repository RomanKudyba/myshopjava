package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.BuysDto;
import my.shop.com.myshopjava.helpers.PaginatorHelper;
import my.shop.com.myshopjava.model.Buys;
import my.shop.com.myshopjava.model.Products;
import my.shop.com.myshopjava.repository.BuysRepository;
import my.shop.com.myshopjava.repository.ProductsRepository;
import my.shop.com.myshopjava.service.BuysService;
import my.shop.com.myshopjava.utils.BuysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BuysServiceImpl implements BuysService {

    private final BuysRepository buysRepository;

    private final BuysMapper buysMapper;

    private final ProductsRepository productsRepository;

    @Autowired
    BuysServiceImpl(
            BuysRepository buysRepository,
            BuysMapper buysMapper,
            ProductsRepository productsRepository){
        this.buysRepository = buysRepository;
        this.buysMapper = buysMapper;
        this.productsRepository = productsRepository;
    }

    @Override
    public BuysDto create(BuysDto buysDto) {
        Buys buy = buysMapper.fromDto(buysDto);
        Products product = productsRepository.findById(buy.getProduct().getId()).orElseThrow();
        buy.setFullPrice(product.getPrice() * buy.getCount());
        Buys newBuy = buysRepository.save(buy);
        return buysMapper.toDto(newBuy);
    }

    @Override
    public BuysDto update(BuysDto buysDto, Long buyId){
        try {
            Buys buy = buysRepository.findById(buyId).orElseThrow(EntityNotFoundException::new);
            buy.setCount(buysDto.getCount());
            buy.setProduct(buysDto.getProduct());
            buy.setProvider(buysDto.getProvider());
            buy.setFullPrice(buysDto.getProduct().getPrice() * buysDto.getCount());

            Buys updatedBuy = buysRepository.save(buy);
            return buysMapper.toDto(updatedBuy);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("buy with id "+buyId+" not found");
        }
    }

    @Override
    public void delete(Long buyId) {
        try {
            Buys buy = buysRepository.findById(buyId).orElseThrow(EntityNotFoundException::new);
            buysRepository.delete(buy);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("buy with id "+buyId+" not found");
        }
    }

    @Override
    public BuysDto get(Long buyId) {
        try {
            Buys buy = buysRepository.findById(buyId).orElseThrow(EntityNotFoundException::new);
            return buysMapper.toDto(buy);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("buy with id "+buyId+" not found");
        }
    }

    @Override
    public Page<Buys> list(HashMap<String, Integer> paginator) {
        Page<Buys> allBuys = buysRepository.findAll(PaginatorHelper.getPageable(paginator));
        return allBuys;
    }

    @Override
    public List<BuysDto> listAll() {
        List<Buys> buys = buysRepository.findAll(Sort.by("id"));
        List<BuysDto> buysDtoList = new ArrayList<>();
        buys.forEach(buyer -> {
            buysDtoList.add(buysMapper.toDto(buyer));
        });
        return buysDtoList;
    }
}
