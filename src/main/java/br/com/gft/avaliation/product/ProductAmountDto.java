package br.com.gft.avaliation.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductAmountDto {
	private String productName;
	private Double totalAmount;
	private Long totalQuantity;
	
}
