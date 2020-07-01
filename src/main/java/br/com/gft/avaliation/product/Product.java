package br.com.gft.avaliation.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("product")
	private String name;

	@JsonProperty
	private Integer quantity;

	@JsonProperty
	private Double price;

	@JsonProperty
	private String type;

	@JsonProperty
	private String industry;

	@JsonProperty
	private String origin;
	
}
