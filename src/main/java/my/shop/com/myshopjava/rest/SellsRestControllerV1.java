package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.model.Sells;
import my.shop.com.myshopjava.service.SellsService;
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
@RequestMapping(value = "/api/v1/admin/sells/")
public class SellsRestControllerV1 {
    @Autowired
    private SellsService sellsService;

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody SellsDto sellsDto) {
        SellsDto newSellDto = sellsService.create(sellsDto);
        return ResponseEntity.ok(newSellDto);
    }

    @PostMapping("{id}/update")
    public ResponseEntity create(@RequestBody SellsDto sellsDto, @PathVariable("id") Long sellId) {
        SellsDto updatedSell = sellsService.update(sellsDto, sellId);
        return ResponseEntity.ok(updatedSell);
    }

    @PostMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long sellId) {
        sellsService.delete(sellId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/get")
    public ResponseEntity get(@PathVariable("id") Long sellId) {
        SellsDto sellsDto = sellsService.get(sellId);
        return ResponseEntity.ok(sellsDto);
    }

    @PostMapping("list")
    public ResponseEntity list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        HashMap<String, Integer> paginator = new HashMap<String, Integer>();
        paginator.put("page", page);
        paginator.put("pageSize", pageSize);
        Page<Sells> sells = sellsService.list(paginator);
        return ResponseEntity.ok(sells);
    }

    @GetMapping("listall")
    public ResponseEntity listAll() {
        List<SellsDto> sellsDtoList = sellsService.listAll();
        return ResponseEntity.ok(sellsDtoList);
    }
}
