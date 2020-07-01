package br.com.gft.avaliation.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders/preview")
public class OrderPreviewController {

	@Autowired
	private OrderPreviewService service;

	@GetMapping
	public ResponseEntity<OrderPreviewResponseVo> previewOrder(
			@RequestParam(value = "quantityShopkeeper", required = true) final Integer quantityShopkeeper,
			@RequestParam(value = "productName", required = true) final String productName) {
		return new ResponseEntity<OrderPreviewResponseVo>(service.calculate(quantityShopkeeper, productName), HttpStatus.OK);
	}

}
