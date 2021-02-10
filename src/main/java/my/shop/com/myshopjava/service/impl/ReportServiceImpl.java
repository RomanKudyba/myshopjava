package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.model.Sells;
import my.shop.com.myshopjava.repository.SellsRepository;
import my.shop.com.myshopjava.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private SellsRepository sellsRepository;

    public Object getBuysWithBuyerByPerid(Map<String, Date> period) {
        Object buys = sellsRepository.getBuysForPeriodByBuyers(period.get("sdate"), period.get("edate"));
        return buys;
    }

}
