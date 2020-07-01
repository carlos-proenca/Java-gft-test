package br.com.gft.avaliation.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.avaliation.product.ProductAmountDto;
import br.com.gft.avaliation.product.ProductService;

@Service
public class OrderPreviewService {

	@Autowired
	private ProductService productService;

	public OrderPreviewResponseVo calculate(final Integer shopkeepers, final String productName) {

		ProductAmountDto productAmount = productService.getProductAmount(productName);

		Double averagePrice = productAmount.getTotalAmount() / productAmount.getTotalQuantity();
		Long productsByShopkeeper = productAmount.getTotalQuantity() / shopkeepers;
		Double totalAmount = productsByShopkeeper * averagePrice;

		return new OrderPreviewResponseVo(productName, productsByShopkeeper, totalAmount, averagePrice);
	}
}
