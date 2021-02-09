package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.ProvidersDto;
import my.shop.com.myshopjava.model.Providers;
import my.shop.com.myshopjava.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/v1/admin/providers/")
public class ProvidersRestController {
    @Autowired
    private ProvidersService providersService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody ProvidersDto providersDto) {
        ProvidersDto newBuyerDto = providersService.create(providersDto);
        return ResponseEntity.ok(newBuyerDto);
    }

    @PostMapping("{id}/update")
    public ResponseEntity create(@RequestBody ProvidersDto providersDto, @PathVariable("id") Long providerId) {
        ProvidersDto updatedProvider = providersService.update(providersDto, providerId);
        return ResponseEntity.ok(updatedProvider);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long providerId) {
        providersService.delete(providerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/get")
    public ResponseEntity get(@PathVariable("id") Long providerId) {
        ProvidersDto providersDto = providersService.get(providerId);
        return ResponseEntity.ok(providersDto);
    }

    @PostMapping("list")
    public ResponseEntity list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        HashMap<String, Integer> paginator = new HashMap<String, Integer>();
        paginator.put("page", page);
        paginator.put("pageSize", pageSize);
        Page<Providers> providers = providersService.list(paginator);
        return ResponseEntity.ok(providers);
    }

    @GetMapping("listall")
    public ResponseEntity listAll() {
        List<ProvidersDto> providersDtoList = providersService.listAll();
        return ResponseEntity.ok(providersDtoList);
    }
}
