package my.shop.com.myshopjava.rest;

import my.shop.com.myshopjava.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/admin/report/")
public class ReportRestControllerV1 {

    @Autowired
    private ReportService reportService;

    @GetMapping("/get-buys-by-buyers")
    public Map<String, Date> getBuysByBuyers(
            @RequestParam("sdate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date sdate,
            @RequestParam("edate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date edate
            ) {
        Map<String, Date> period = new HashMap<>();
        period.put("sdate", sdate);
        period.put("edate", edate);
        String result = reportService.getBuysWithBuyerByPerid(period);
        return period;
    }
}
