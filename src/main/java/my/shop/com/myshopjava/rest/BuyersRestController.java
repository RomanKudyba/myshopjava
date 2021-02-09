package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.BuyersDto;
import my.shop.com.myshopjava.model.Buyers;
import my.shop.com.myshopjava.service.BuyersService;
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
@RequestMapping(value = "/api/v1/admin/buyers/")
public class BuyersRestController {
    @Autowired
    private BuyersService buyersService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody BuyersDto buyersDto) {
        BuyersDto newBuyerDto = buyersService.create(buyersDto);
        return ResponseEntity.ok(newBuyerDto);
    }

    @PostMapping("{id}/update")
    public ResponseEntity create(@RequestBody BuyersDto buyersDto, @PathVariable("id") Long buyerId) {
        BuyersDto updatedBuyer = buyersService.update(buyersDto, buyerId);
        return ResponseEntity.ok(updatedBuyer);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long buyerId) {
        buyersService.delete(buyerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/get")
    public ResponseEntity get(@PathVariable("id") Long buyerId) {
        BuyersDto buyersDto = buyersService.get(buyerId);
        return ResponseEntity.ok(buyersDto);
    }

    @PostMapping("list")
    public ResponseEntity list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        HashMap<String, Integer> paginator = new HashMap<String, Integer>();
        paginator.put("page", page);
        paginator.put("pageSize", pageSize);
        Page<Buyers> buyers = buyersService.list(paginator);
        return ResponseEntity.ok(buyers);
    }

    @GetMapping("listall")
    public ResponseEntity listAll() {
        List<BuyersDto> buyersDtoList = buyersService.listAll();
        return ResponseEntity.ok(buyersDtoList);
    }
}
