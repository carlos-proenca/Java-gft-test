package br.com.gft.avaliation.order;

import lombok.Data;

//@Data
public class OrderPreviewResponseVo {
	
	private String productName;
	
	private Long quantity;
	
	private Double totalAmount;
	
	private Double productAveragePrice;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getProductAveragePrice() {
		return productAveragePrice;
	}

	public void setProductAveragePrice(Double productAveragePrice) {
		this.productAveragePrice = productAveragePrice;
	}

	@Override
	public String toString() {
		return "OrderPreviewResponseVo [productName=" + productName + ", quantity=" + quantity + ", totalAmount="
				+ totalAmount + ", productAveragePrice=" + productAveragePrice + "]";
	}

}
