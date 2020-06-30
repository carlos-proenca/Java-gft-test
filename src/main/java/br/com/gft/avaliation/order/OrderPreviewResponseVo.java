package br.com.gft.avaliation.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderPreviewResponseVo {
	
	private String productName;
	
	private Long quantity;
	
	private Double totalAmount;
	
	private Double productAveragePrice;

}
