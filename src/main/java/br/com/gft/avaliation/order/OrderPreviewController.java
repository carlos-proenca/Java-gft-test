package br.com.gft.avaliation.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/orders/preview")
public class OrderPreviewController {
	
	@Autowired
	private OrderPreviewService service;
	
	@GetMapping
	public ResponseEntity<List<OrderPreviewResponseVo> > previewOrder() {
		return new ResponseEntity<List<OrderPreviewResponseVo>>(service.calculate(), HttpStatus.OK);
	}

}
