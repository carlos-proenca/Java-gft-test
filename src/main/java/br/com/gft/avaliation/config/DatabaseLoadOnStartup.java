//package br.com.gft.avaliation.config;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.gft.avaliation.model.Product;
//import br.com.gft.avaliation.service.ProductService;
//
//@Component
//public class DatabaseLoadOnStartup implements CommandLineRunner {
//
//	private static final String BASE_PATH = "src/main/resources/data/";
//	
//	@Autowired
//	private ProductService productService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Database loading.....");
//		loadDatasource("data_1.json");
////		loadDatasource("data_2.json");
////		loadDatasource("data_3.json");
////		loadDatasource("data_4.json");
//	}
//
//	@Async
//	public CompletableFuture<List<Product>> loadDatasource(String datasource) throws Exception {
//		List<Product> products = loadDataFromJsonFile(BASE_PATH + datasource);
//		return CompletableFuture.completedFuture(products);
//	}
//
//	private List<Product> loadDataFromJsonFile(String datasource) throws Exception {
//		final List<Product> products = new ArrayList<>();
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		Long lastField = 1l;
//
//		try (final BufferedReader br = new BufferedReader(new FileReader(datasource))) {
//			saveProducts(products, objectMapper, lastField, br);
//			return products;
//		} catch (final IOException e) {
//			System.out.println("Saving a list of cars of size {} records " + e);
//			throw new Exception("Failed to parse CSV file {}", e);
//		}
//	}
//
//	private void saveProducts(final List<Product> products, ObjectMapper objectMapper, Long lastField,
//			final BufferedReader br) throws JsonMappingException, JsonProcessingException, IOException {
//		try {
//			String line = "";
//			saveFirstProduct(products, objectMapper, br);
//			
//			while ((line = br.readLine()) != null) {
//				line = line.replace("$", "");
//				Product product = objectMapper.readValue(line, Product.class);
//				productService.save(product);
//				System.out.println("the line is " + product);
//				lastField++;
//			}
//		} catch (DataIntegrityViolationException e) {
//			System.out.println("the product already exists " +e);
//		}
//	}
//
//	private void saveFirstProduct(final List<Product> products, ObjectMapper objectMapper, final BufferedReader br)
//			throws IOException, JsonProcessingException, JsonMappingException {
//		String line = br.readLine();
//		line = line.replace("{\"data\":[", "");
//		line = line.replace("$", "");
//		Product product = objectMapper.readValue(line, Product.class);
//		productService.save(product);
//	}
//}
