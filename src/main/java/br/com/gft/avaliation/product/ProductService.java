package br.com.gft.avaliation.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * TODO
 * 
 * @author Carlos Proença
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public List<Product> searchProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(final Long id) {
		return productRepository.findById(id);
	}
	
	public ProductAmountDto getProductAmount(final String name) {
		
		List<Product> products = productRepository.findByName(name);
		
		products.forEach( product -> System.out.println("the product loaded is "+ product));
		
		ProductAmountDto productAmountDto = new ProductAmountDto(name,getProductTotalAmount(products),getProductTotalQuantity(products) );
		
		return productAmountDto;
	}

	private Double getProductTotalAmount(final List<Product> products) {
		return products.stream()
				  .mapToDouble(x -> x.getPrice())
				  .sum();
	}

	private Long getProductTotalQuantity(final List<Product> products) {
		return products.stream()
				  .mapToLong(x -> x.getQuantity())
				  .sum();
	}
}
