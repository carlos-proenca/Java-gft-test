package br.com.gft.avaliation.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderPreviewController {

	@Autowired
	private OrderPreviewService service;

	@GetMapping("api/orders/preview/{quantityShopkeeper}/{productName}")
	public ResponseEntity<List<OrderPreviewResponseVo>> previewOrder(
			@PathVariable("quantityShopkeeper") Integer quantityShopkeeper,
			@PathVariable("productName") String productName) {
		return new ResponseEntity<List<OrderPreviewResponseVo>>(service.calculate(quantityShopkeeper, productName), HttpStatus.OK);
	}

}
