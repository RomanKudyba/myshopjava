package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.model.Sells;

import java.util.Date;
import java.util.Map;

public interface ReportService {
    Object getBuysWithBuyerByPerid(Map<String, Date> period);
}
