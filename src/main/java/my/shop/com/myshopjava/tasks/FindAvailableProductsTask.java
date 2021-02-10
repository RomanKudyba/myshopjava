package my.shop.com.myshopjava.tasks;

import my.shop.com.myshopjava.model.Buys;
import my.shop.com.myshopjava.model.Products;
import my.shop.com.myshopjava.model.Sells;
import my.shop.com.myshopjava.repository.BuysRepository;
import my.shop.com.myshopjava.repository.ProductsRepository;
import my.shop.com.myshopjava.repository.SellsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FindAvailableProductsTask extends ScheduledTasks{

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private BuysRepository buysRepository;

    @Autowired
    private SellsRepository sellsRepository;

    @Scheduled(cron = "* * * * * ?")
    public void findAvailvableProductTask() {
        List<Products> allProducts = productsRepository.findAll();
        List<Sells> allSells = sellsRepository.findAll();
        List<Buys> allBuys = buysRepository.findAll();

        Map<Products, List<Sells>> sellsByProduct = allSells.stream().collect(
                Collectors.groupingBy(Sells::getProduct));

        Map<Products, List<Buys>> buysByProduct = allBuys.stream().collect(
                Collectors.groupingBy(Buys::getProduct));
//@todo
        List<Integer> integers = new ArrayList<>();
        allProducts.forEach(product -> {
            Integer productSellsCount = 0;
//            sellsByProduct.entrySet().stream().filter(p -> p.getKey().getId().equals(product.getId()))
//                    .map(Map.Entry::getValue).forEach(
//                            q -> q.forEach(w -> w.getCount())
//            );

            sellsByProduct.entrySet().stream().filter(p -> p.getKey().getId().equals(product.getId()))
                    .map(Map.Entry::getValue).forEach(q -> q.forEach(w -> integers.add(w.getCount())));
            Integer sum = integers.stream().collect(Collectors.summingInt(Integer::intValue));
            System.out.println(sum);
//                    .map(c ->
//                    c.getValue().stream().map(l -> integers.add(l.getCount()))
//
//
//            );


//            System.out.println(integers);


            for (Map.Entry<Products, List<Sells>> pair : sellsByProduct.entrySet()) {
                if (pair.getKey().getId().equals(product.getId())) {
                    for (Sells value : pair.getValue()) {
                        productSellsCount += value.getCount();
                    }
                }
            }

            Integer productBuysCount = 0;
            for (Map.Entry<Products, List<Buys>> pair : buysByProduct.entrySet()) {
                if (pair.getKey().getId().equals(product.getId())) {
                    for (Buys value : pair.getValue()) {
                        productBuysCount += value.getCount();
                    }
                }
            }
            boolean availabe = false;
            if ((productBuysCount - productSellsCount) > 0){
                availabe = true;
            }
            product.setAvailable(availabe);
            productsRepository.save(product);
            log.info("product available set to : {}", product.getAvailable());
        });
    }

//        allProducts.forEach(product -> {
//            Integer productSellsCount = 0;
//            for (Map.Entry<Products, List<Sells>> pair : sellsByProduct.entrySet()) {
//                if (pair.getKey().getId().equals(product.getId())) {
//                    for (Sells value : pair.getValue()) {
//                        productSellsCount += value.getCount();
//                    }
//                }
//            }
//
//            Integer productBuysCount = 0;
//            for (Map.Entry<Products, List<Buys>> pair : buysByProduct.entrySet()) {
//                if (pair.getKey().getId().equals(product.getId())) {
//                    for (Buys value : pair.getValue()) {
//                        productBuysCount += value.getCount();
//                    }
//                }
//            }
//            boolean availabe = false;
//            if ((productBuysCount - productSellsCount) > 0){
//                availabe = true;
//            }
//            product.setAvailable(availabe);
//            productsRepository.save(product);
//            log.info("product available set to : {}", product.getAvailable());
//        });
//    }
}
