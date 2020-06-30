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
	
	public List<OrderPreviewResponseVo> calculate() {
		
		List<OrderPreviewResponseVo> shopkeepersPreview = new ArrayList<>();
		Integer shopkeepers = 2;
		
		ProductAmountDto productAmount = productService.getProductAmount("EMMS");
		
		Double averagePrice = productAmount.getTotalAmount() / productAmount.getTotalQuantity();
		Long productsByShopkeeper = productAmount.getTotalQuantity() / shopkeepers;
		
		
		OrderPreviewResponseVo orderPreviewResponseVo = new OrderPreviewResponseVo();
		orderPreviewResponseVo.setProductAveragePrice(averagePrice);
		orderPreviewResponseVo.setQuantity(productsByShopkeeper);
		orderPreviewResponseVo.setTotalAmount(productsByShopkeeper * averagePrice);
		orderPreviewResponseVo.setProductName("EMMS");
		
		shopkeepersPreview.add(orderPreviewResponseVo);
		shopkeepersPreview.add(orderPreviewResponseVo);
		
		return shopkeepersPreview;
	}
}
