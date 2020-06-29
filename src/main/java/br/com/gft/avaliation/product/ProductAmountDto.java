package br.com.gft.avaliation.product;

//@Data
public class ProductAmountDto {
	private String productName;
	private Double totalAmount;
	private Long totalQuantity;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	@Override
	public String toString() {
		return "ProductAmountDto [productName=" + productName + ", totalAmount=" + totalAmount + ", totalQuantity="
				+ totalQuantity + "]";
	}
}
