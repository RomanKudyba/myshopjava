package my.shop.com.myshopjava.service.impl;

import my.shop.com.myshopjava.dto.ProductDto;
import my.shop.com.myshopjava.dto.SellsDto;
import my.shop.com.myshopjava.helpers.PaginatorHelper;
import my.shop.com.myshopjava.model.*;
import my.shop.com.myshopjava.repository.BuyersRepository;
import my.shop.com.myshopjava.repository.BuysRepository;
import my.shop.com.myshopjava.repository.ProductsRepository;
import my.shop.com.myshopjava.repository.SellsRepository;
import my.shop.com.myshopjava.service.ProductsService;
import my.shop.com.myshopjava.utils.ProductsMapper;
import my.shop.com.myshopjava.utils.SellsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private BuyersRepository buyersRepository;

    @Autowired
    private SellsRepository sellsRepository;

    @Autowired
    private BuysRepository buysRepository;

    @Autowired
    private ProductsMapper productMapper;

    @Autowired
    private SellsMapper sellMapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Products newProduct = productsRepository.save(productMapper.fromDto(productDto));
        return productMapper.toDto(newProduct);
    }

    @Override
    public ProductDto update(ProductDto productDto, Long productId){
        try {
            Products product = productsRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setCategory(productDto.getCategory());
            Products updatedProduct = productsRepository.save(product);
            return productMapper.toDto(updatedProduct);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("product with id "+productId+" not found");
        }
    }

    @Override
    public void delete(Long productId) {
        try {
            Products product = productsRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
            productsRepository.delete(product);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("product with id "+productId+" not found");
        }
    }

    @Override
    public ProductDto get(Long productId) {
        try {
            Products product = productsRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
            return productMapper.toDto(product);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("product with id "+productId+" not found");
        }
    }

    @Override
    public Page<Products> list(HashMap<String, Integer> paginator) {
        Page<Products> allProducts = productsRepository.findAll(PaginatorHelper.getPageable(paginator));
        return allProducts;
    }

    @Override
    public List<ProductDto> listAll() {
        List<Products> products = productsRepository.findAll(Sort.by("id"));
        List<ProductDto> productDtoList = new ArrayList<>();
        products.forEach(product -> {
            productDtoList.add(productMapper.toDto(product));
        });
        return productDtoList;
    }

    @Override
    public SellsDto buy(Long productId, String username, Sells sell) throws Exception{
        Buyers buyer = buyersRepository.findByFirstName(username);
        if (buyer == null) {
            throw new Exception("buyer by name " + username + " not found");
        }

        Products product = productsRepository.findById(productId).orElseThrow(() -> new Exception("product by id " + productId + " not found"));

        if (product.getAvailable() == false) {
            throw  new Exception("product is not available");
        }

        Integer count = sell.getCount();
        Integer availableCountProduct = getAvailableProductsCount(productId);

        if (count > availableCountProduct) {
            throw new Exception("to many products");
        }

        Sells newSell = new Sells();

        newSell.setProduct(product);
        newSell.setBuyer(buyer);
        newSell.setCount(count);
        Sells insertedSell = sellsRepository.save(newSell);
        return sellMapper.toDto(insertedSell);
    }

    private Integer getAvailableProductsCount(Long productId){
        Integer buysCount = 0;
        ArrayList<Buys> buys = buysRepository.findByProductId(productId);
        for (Buys buyV : buys) {
            buysCount += buyV.getCount();
        }

        Integer sellsCount = 0;
        ArrayList<Sells> sells = sellsRepository.findByProductId(productId);
        for (Sells sellV : sells) {
            sellsCount += sellV.getCount();
        }

        return buysCount - sellsCount;
    }
}
