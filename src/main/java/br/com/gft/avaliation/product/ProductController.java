package br.com.gft.avaliation.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("{id}")
	public ResponseEntity<Product> findOne(@PathVariable final Long id) {
		return new ResponseEntity<Product>(service.findById(id).get(), HttpStatus.OK);
	}
}
