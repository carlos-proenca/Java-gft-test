package br.com.gft.avaliation.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * TODO
 * 
 * @author Carlos Proença
 *
 */
interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByName(String name);
}
