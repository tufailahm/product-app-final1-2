package com.training.pms.galaxe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String saveProduct(Product product) {
		//security			- 10 lines
		//logging 			- 4 lines
		//transaction 	- 
		String message =null;
		if(product.getPrice() <0 | product.getQuantityOnHand() < 0 )
		{
			message = "Product price or QOH cannot be negative. Not saved.";
		}
		else
		{
			message = "Product Saved successfully. You are genious";
			productDAO.save(product);

		}
		return message;
	}
	// To be continued in online mode .... ####
	@Override
	public String updateProduct(Product product) {
		//security			- 10 lines
		String message =null;
		if(product.getPrice() <0 | product.getQuantityOnHand() < 0 )
		{
			message = "Product price or QOH cannot be negative. Not Updated.";
		}
		else
		{
			message = "Product Updated successfully.";
			productDAO.save(product);

		}
		return message;
	}
	@Override
	public String deleteProduct(int productId) {
		//security			- 10 lines
		productDAO.deleteById(productId);
		return "Product deleted successfully";
	}
	@Override
	public Product getProduct(int productId) {				//199
		Optional<Product> pr = productDAO.findById(productId);
		return pr.get();
	}
	@Override
	public List<Product> getProduct() {					//get all products
		System.out.println("GET ALL Products called");
		return (List<Product>) productDAO.findAll();
	}
	@Override
	public boolean isProductExists(int productId) {
		Optional<Product> pr = productDAO.findById(productId);
		return pr.isPresent();
	}

	
	@Override
	public List<Product> searchProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Product> getProduct(String productName) {
		return productDAO.findByProductName(productName);
	}



	@Override
	public List<Product> getProduct(int min, int max) {
		// TODO Auto-generated method stub
		return productDAO.findByPriceBetween(min, max);
	}

	@Override
	public String checkProductInventory(int productId, int quantityRequired) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByPrice(int price) {
		// TODO Auto-generated method stub
		return productDAO.findByPrice(price);
	}

}
