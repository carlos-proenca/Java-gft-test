package br.com.gft.avaliation.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gft.avaliation.product.ProductAmountDto;
import br.com.gft.avaliation.product.ProductService;

@RunWith(SpringRunner.class)
public class OrderPreviewServiceTest {
	
	@InjectMocks
	private OrderPreviewService service;
	
	@Mock
	private ProductService productService;
	
	@Test
	public void calculateTest() {
		when(productService.getProductAmount(Mockito.anyString())).thenReturn(getProductAmoutnTest());
		OrderPreviewResponseVo orderPreviewResponse = service.calculate(5, "");
		assertNotNull(orderPreviewResponse);
		assertEquals(1, orderPreviewResponse.getQuantity());
		assertEquals(20, orderPreviewResponse.getProductAveragePrice());
		assertEquals(20, orderPreviewResponse.getTotalAmount());
	}

	private ProductAmountDto getProductAmoutnTest() {
		return new ProductAmountDto("Product Test", 100.0, new Long(5));
	}
}
