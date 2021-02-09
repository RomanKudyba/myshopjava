package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.DTO;
import my.shop.com.myshopjava.model.BaseEntity;

import java.util.Collection;

public interface BaseMapper {
    Collection<? extends DTO> toDTO(BaseEntity entity);
    BaseEntity fromDto(DTO dto);
}
