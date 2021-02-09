package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.DTO;
import my.shop.com.myshopjava.model.BaseEntity;
import my.shop.com.myshopjava.service.BaseService;

import java.util.Collection;

public class BaseServiceImpl implements BaseService {
    public Collection<? extends DTO> toDto(Collection<? extends BaseEntity> entity){
        entity.forEach(entityOne -> {

        });
        return null;
    }
}
