package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.dto.ProvidersDto;
import my.shop.com.myshopjava.model.Providers;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface ProvidersService{
    ProvidersDto create(ProvidersDto providersDto);

    ProvidersDto update(ProvidersDto providersDto, Long providerId);

    void delete(Long providerId);

    ProvidersDto get(Long providerId);

    Page<Providers> list(HashMap<String, Integer> paginator);

    List<ProvidersDto> listAll();
}
