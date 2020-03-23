package com.iessanvicente.scondhandmarket;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.repositories.PurchaseRepository;
import com.iessanvicente.scondhandmarket.services.ProductService;
import com.iessanvicente.scondhandmarket.services.UserService;

@SpringBootApplication
public class SecondHandMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondHandMarketApplication.class, args);
	}
	
	/*
	@Bean
	public CommandLineRunner initData(UserRepository userRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
		return args ->{
			User user = new User("Sergio", "Rueskas Perez", null, "Rueskas", "1234");
			userRepository.save(user);
			productRepository.saveAll(Arrays.asList(
					new Product("Xbox One X", 425.0f, "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE3ys5u?ver=8a64", user),
					new Product("Iphone 7 128 GB", 350.0f, "https://csmobiles.com/2716-large_default/apple-iphone-7-32gb-negro.jpg", user),
					new Product("Golf GTI Serie 2", 2500.0f, "https://1.bp.blogspot.com/-Xz4UIQwr9ZY/UoNd4vecnBI/AAAAAAAAAE4/sp_IVTAGyX8/s1600/golf_5.jpg", user),
					new Product("Play Station 4 Pro", 250.0f, "https://images-na.ssl-images-amazon.com/images/I/51w3D9iVS1L._AC_SX679_.jpg", user)));
			
		};
	}
	*/


	@Bean
	public CommandLineRunner initData(UserService userService, ProductService productService, PurchaseRepository purchaseRepository) {
		return args ->{
			User user1 = new User("Sergio", "Rueskas Perez", null, "Rueskas", "1234");
			User user2 = new User("Antonio", "Arroyo Pastor", null, "Arroyo", "1234");
			userService.register(user1);
			userService.register(user2);
			List<Product> products = Arrays.asList(
					new Product("Xbox One X", 425.0f, "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE3ys5u?ver=8a64", user1),
					new Product("Xbox One X", 425.0f, "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE3ys5u?ver=8a64", user2),
					new Product("Iphone 7 128 GB", 350.0f, "https://csmobiles.com/2716-large_default/apple-iphone-7-32gb-negro.jpg", user1),
					new Product("Iphone 7 32 GB", 350.0f, "https://csmobiles.com/2716-large_default/apple-iphone-7-32gb-negro.jpg", user2),
					new Product("Golf GTI Serie 2", 2500.0f, "https://1.bp.blogspot.com/-Xz4UIQwr9ZY/UoNd4vecnBI/AAAAAAAAAE4/sp_IVTAGyX8/s1600/golf_5.jpg", user2),
					new Product("Play Station 4 Pro", 250.0f, "https://images-na.ssl-images-amazon.com/images/I/51w3D9iVS1L._AC_SX679_.jpg", user2));
			products.forEach(productService::insert);
		};
	}
}
