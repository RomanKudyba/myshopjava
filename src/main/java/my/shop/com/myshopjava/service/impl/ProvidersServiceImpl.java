package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.ProvidersDto;
import my.shop.com.myshopjava.helpers.PaginatorHelper;
import my.shop.com.myshopjava.model.Providers;
import my.shop.com.myshopjava.repository.ProvidersRepository;
import my.shop.com.myshopjava.service.ProvidersService;
import my.shop.com.myshopjava.utils.ProvidersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProvidersServiceImpl implements ProvidersService {
    @Autowired
    private ProvidersRepository providersRepository;

    @Autowired
    private ProvidersMapper providersMapper;

    @Override
    public ProvidersDto create(ProvidersDto providersDto) {
        Providers newProvider = providersRepository.save(providersMapper.fromDto(providersDto));
        return providersMapper.toDto(newProvider);
    }

    @Override
    public ProvidersDto update(ProvidersDto providersDto, Long providerId){
        try {
            Providers provider = providersRepository.findById(providerId).orElseThrow(EntityNotFoundException::new);
            provider.setFirstName(providersDto.getFirstName());
            provider.setMidName(providersDto.getMidName());
            provider.setSurname(providersDto.getSurname());
            provider.setEmail(providersDto.getEmail());
            provider.setAge(providersDto.getAge());
            provider.setPhone(providersDto.getPhone());
            Providers updatedProvider = providersRepository.save(provider);
            return providersMapper.toDto(updatedProvider);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("provider with id "+providerId+" not found");
        }
    }

    @Override
    public void delete(Long providerId) {
        try {
            Providers provider = providersRepository.findById(providerId).orElseThrow(EntityNotFoundException::new);
            providersRepository.delete(provider);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("provider with id "+providerId+" not found");
        }
    }

    @Override
    public ProvidersDto get(Long providerId) {
        try {
            Providers provider = providersRepository.findById(providerId).orElseThrow(EntityNotFoundException::new);
            return providersMapper.toDto(provider);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("provider with id "+providerId+" not found");
        }
    }

    @Override
    public Page<Providers> list(HashMap<String, Integer> paginator) {
        Page<Providers> allProviders = providersRepository.findAll(PaginatorHelper.getPageable(paginator));
        return allProviders;
    }

    @Override
    public  List<ProvidersDto> listAll() {
        List<Providers> providers = providersRepository.findAll(Sort.by("id"));
        List<ProvidersDto> providersDtoList = new ArrayList<>();
        providers.forEach(provider -> {
            providersDtoList.add(providersMapper.toDto(provider));
        });
        return providersDtoList;
    }
}
