package my.shop.com.myshopjava.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public class PaginatorHelper {
    public  static Integer getDefaultPage(){
        return 0;
    }

    public static Integer getDefaultPageSize(){
        return 10;
    }

    public static Pageable getPageable(HashMap<String, Integer> paginator)
    {
        Pageable pageable = PageRequest.of(paginator.get("page"), paginator.get("pageSize"), Sort.by("id"));
        return pageable;
    }
}
