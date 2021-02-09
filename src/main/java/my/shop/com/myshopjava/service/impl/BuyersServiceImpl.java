package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.BuyersDto;
import my.shop.com.myshopjava.helpers.PaginatorHelper;
import my.shop.com.myshopjava.model.Buyers;
import my.shop.com.myshopjava.repository.BuyersRepository;
import my.shop.com.myshopjava.service.BuyersService;
import my.shop.com.myshopjava.utils.BuyersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BuyersServiceImpl implements BuyersService {
    @Autowired
    private BuyersRepository buyersRepository;

    @Autowired
    private BuyersMapper buyersMapper;

    @Override
    public BuyersDto create(BuyersDto buyersDto) {
        Buyers newBuyer = buyersRepository.save(buyersMapper.fromDto(buyersDto));
        return buyersMapper.toDto(newBuyer);
    }

    @Override
    public BuyersDto update(BuyersDto buyersDto, Long buyerId){
        try {
            Buyers buyer = buyersRepository.findById(buyerId).orElseThrow(EntityNotFoundException::new);
            buyer.setFirstName(buyersDto.getFirstName());
            buyer.setMidName(buyersDto.getMidName());
            buyer.setSurname(buyersDto.getSurname());
            buyer.setEmail(buyersDto.getEmail());
            buyer.setAge(buyersDto.getAge());
            buyer.setPhone(buyersDto.getPhone());
            Buyers updatedBuyer = buyersRepository.save(buyer);
            return buyersMapper.toDto(updatedBuyer);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("buyer with id "+buyerId+" not found");
        }
    }

    @Override
    public void delete(Long buyerId) {
        try {
            Buyers buyer = buyersRepository.findById(buyerId).orElseThrow(EntityNotFoundException::new);
            buyersRepository.delete(buyer);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("buyer with id "+buyerId+" not found");
        }
    }

    @Override
    public BuyersDto get(Long buyerId) {
        try {
            Buyers buyer = buyersRepository.findById(buyerId).orElseThrow(EntityNotFoundException::new);
            return buyersMapper.toDto(buyer);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("buyer with id "+buyerId+" not found");
        }
    }

    @Override
    public Page<Buyers> list(HashMap<String, Integer> paginator) {
        Page<Buyers> allBuyers = buyersRepository.findAll(PaginatorHelper.getPageable(paginator));
        return allBuyers;
    }

    @Override
    public  List<BuyersDto> listAll() {
        List<Buyers> buyers = buyersRepository.findAll(Sort.by("id"));
        List<BuyersDto> buyersDtoList = new ArrayList<>();
        buyers.forEach(buyer -> {
            buyersDtoList.add(buyersMapper.toDto(buyer));
        });
        return buyersDtoList;
    }
}
