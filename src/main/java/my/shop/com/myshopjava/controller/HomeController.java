package my.shop.com.myshopjava.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> greeting() {
        return Collections.singletonMap("message", "Hello, this is shop");
    }

}