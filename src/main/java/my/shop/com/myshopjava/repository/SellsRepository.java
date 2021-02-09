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

    @Query("" +
            "Select " +
            "products.name," +
            "sells.buyer_id," +
            "count(*) as count " +
            "from " +
            "sells " +
            "join " +
            "products " +
            "on products.id = sells.product_id " +
            "where " +
            "sells.created >= ?1 AND sells.created <= ?2 " +
            "Group by " +
            "products.name, sells.buyer_id"
    )
    Sells getBuysForPeriodByBuyers(Date sdate, Date edate);
}
