package com.training.pms.galaxe.model;

import java.util.Date;

import lombok.Data;

@Data
public class Orders {

	private int orderId;
	private String orderDescription;
	private Date orderDate;
	private String orderStatus;

}
