package br.com.gft.avaliation.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	
	@Test
	public void getProductAmountTest() {
		when(repository.findByName(Mockito.anyString())).thenReturn(getProductsMock());
		ProductAmountDto amountDto =  service.getProductAmount("Teste");
		assertEquals(20, amountDto.getTotalAmount());
		assertEquals(5, amountDto.getTotalQuantity());
	}

	private List<Product> getProductsMock() {
		List<Product> productsList =  new ArrayList<Product>();
		Product product1 =  new Product(new Long(1), "Product Test",2, 10.0, "Product Type", "Industry Test", "Origin Test");
		Product product2 =  new Product(new Long(2), "Product Test",3, 10.0, "Product Type", "Industry Test", "Origin Test");
		productsList.add(product1);
		productsList.add(product2);
		return productsList;
	}

}
