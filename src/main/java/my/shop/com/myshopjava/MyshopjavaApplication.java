package my.shop.com.myshopjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class MyshopjavaApplication {

	public static void main(String[] args) {
//		Map<String, String> capitals = new HashMap<>();
//
//		capitals.put("svk", "Bratislava");
//		capitals.put("ger", "Berlin");
//		capitals.put("hun", "Budapest");
//		capitals.put("czk", "Prague");
//		capitals.put("pol", "Warsaw");
//		capitals.put("ita", "Rome");
//
//		Map<String, String> filteredCapitals = capitals.entrySet().stream()
//				.filter(map ->  map.getValue().startsWith("B"))
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//		filteredCapitals.entrySet().forEach(System.out::println);
		SpringApplication.run(MyshopjavaApplication.class, args);
	}

}
