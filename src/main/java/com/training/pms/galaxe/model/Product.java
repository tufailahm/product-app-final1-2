package com.training.pms.galaxe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "freshproducts")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;

}
