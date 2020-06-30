package br.com.gft.avaliation.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.avaliation.product.ProductAmountDto;
import br.com.gft.avaliation.product.ProductService;

/**
 * 
 * TODO
 * 
 * @author Carlos Proença
 *
 */
@Service
public class OrderPreviewService {

	@Autowired
	private ProductService productService;

	public List<OrderPreviewResponseVo> calculate(final Integer shopkeepers, final String productName) {

		List<OrderPreviewResponseVo> shopkeepersPreview = new ArrayList<>();
		ProductAmountDto productAmount = productService.getProductAmount(productName);

		Double averagePrice = productAmount.getTotalAmount() / productAmount.getTotalQuantity();
		Long productsByShopkeeper = productAmount.getTotalQuantity() / shopkeepers;

		Double a = productsByShopkeeper * averagePrice;
		
		OrderPreviewResponseVo orderPreviewResponseVo = new OrderPreviewResponseVo(productName, productsByShopkeeper, a,
				averagePrice);

		shopkeepersPreview.add(orderPreviewResponseVo);

		return shopkeepersPreview;
	}
}
