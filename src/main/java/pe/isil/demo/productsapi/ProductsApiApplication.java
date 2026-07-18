package pe.isil.demo.productsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Products API",
				version = "1.0",
				description = "API documentation for managing products"
		),
		servers = {
				@Server(
						url = "https://products-api-6997-1015952539697.southamerica-east1.run.app/",
						description = "Production server"
				)
		}
)
@SpringBootApplication
public class ProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiApplication.class, args);
	}

}
