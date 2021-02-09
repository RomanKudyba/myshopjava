package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.BuysDto;
import my.shop.com.myshopjava.model.Buys;
import my.shop.com.myshopjava.service.BuysService;
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
@RequestMapping(value = "/api/v1/admin/buys/")
public class BuysRestControllerV1 {
    @Autowired
    private BuysService buysService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody BuysDto buysDto) {
        BuysDto newBuyerDto = buysService.create(buysDto);
        return ResponseEntity.ok(newBuyerDto);
    }

    @PostMapping("{id}/update")
    public ResponseEntity create(@RequestBody BuysDto buysDto, @PathVariable("id") Long buyId) {
        BuysDto updatedBuy = buysService.update(buysDto, buyId);
        return ResponseEntity.ok(updatedBuy);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long buyId) {
        buysService.delete(buyId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/get")
    public ResponseEntity get(@PathVariable("id") Long buyId) {
        BuysDto buysDto = buysService.get(buyId);
        return ResponseEntity.ok(buysDto);
    }

    @PostMapping("list")
    public ResponseEntity list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        HashMap<String, Integer> paginator = new HashMap<String, Integer>();
        paginator.put("page", page);
        paginator.put("pageSize", pageSize);
        Page<Buys> buys = buysService.list(paginator);
        return ResponseEntity.ok(buys);
    }

    @GetMapping("listall")
    public ResponseEntity listAll() {
        List<BuysDto> buyersDtoList = buysService.listAll();
        return ResponseEntity.ok(buyersDtoList);
    }
}
