package my.shop.com.myshopjava.service;

import java.util.Date;
import java.util.Map;

public interface ReportService {
    String getBuysWithBuyerByPerid(Map<String, Date> period);
}
