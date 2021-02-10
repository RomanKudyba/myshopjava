package my.shop.com.myshopjava.repository;

import my.shop.com.myshopjava.model.Buys;
import my.shop.com.myshopjava.model.Sells;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public interface SellsRepository extends JpaRepository<Sells, Long> {
    ArrayList<Sells> findByProductId(Long productId);

    //@todo
    @Query("" +
            "Select " +
            "products.name," +
            "sells.buyer," +
            "count(*) as count " +
            "from " +
            "Sells sells " +
            "join " +
            "Products products " +
            "on products.id = sells.product " +
            "where " +
            "sells.created >= ?1 AND sells.created <= ?2 " +
            "Group by " +
            "products.name, sells.buyer"
    )
    Object getBuysForPeriodByBuyers(Date sdate, Date edate);
}
