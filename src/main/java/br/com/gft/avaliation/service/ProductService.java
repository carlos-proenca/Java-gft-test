package br.com.gft.avaliation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.avaliation.model.Product;
import br.com.gft.avaliation.repository.ProductRepository;

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

}
