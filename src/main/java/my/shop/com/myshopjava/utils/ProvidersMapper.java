package my.shop.com.myshopjava.utils;

import my.shop.com.myshopjava.dto.ProvidersDto;
import my.shop.com.myshopjava.model.Providers;
import org.springframework.stereotype.Component;

@Component("providersMapper")
public class ProvidersMapper {

    public ProvidersDto toDto(Providers provider){
        ProvidersDto providersDto = new ProvidersDto();
        providersDto.setFirstName(provider.getFirstName());
        providersDto.setMidName(provider.getMidName());
        providersDto.setSurname(provider.getSurname());
        providersDto.setAge(provider.getAge());
        providersDto.setEmail(provider.getEmail());
        providersDto.setPhone(provider.getPhone());

        return providersDto;
    }

    public Providers fromDto(ProvidersDto providersDto){
        Providers provider = new Providers();
        provider.setFirstName(providersDto.getFirstName());
        provider.setMidName(providersDto.getMidName());
        provider.setSurname(providersDto.getSurname());
        provider.setAge(providersDto.getAge());
        provider.setEmail(providersDto.getEmail());
        provider.setPhone(providersDto.getPhone());

        return provider;
    }
}
