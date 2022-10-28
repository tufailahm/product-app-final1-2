package com.training.pms.galaxe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;
import com.training.pms.galaxe.service.ProductService;

@RestController
@RequestMapping("product")
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	ProductService productService;

	String message;

	@Autowired
	Product product;

	@PostMapping // http://localhost:9090/product/ - POST - BODY
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;

		if (productService.isProductExists(product.getProductId())) {
			responseEntity = new ResponseEntity<String>("Product already exists", HttpStatus.CONFLICT); // 409
		} else {

			String message = productService.saveProduct(product);
			if (message.equals("Product Saved successfully. You are genious")) {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.CREATED); // 201
			} else {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_ACCEPTABLE); // 406
			}
		}
		return responseEntity;
	}

	@GetMapping // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProducts() {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct();

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}
	
	
	//Fetch products by product name
	//http://localhost:9090/product/searchByProductName/Car
	@GetMapping("searchByProductName/{productName}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("productName") String productName) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct(productName);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}
	
	
	//http://localhost:9090/product/searchByProductPriceRange/1000/3000
	
	//??
	@GetMapping("searchByProductPriceRange/{minPrice}/and/{maxPrice}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("minPrice") int minPrice,
			@PathVariable("maxPrice") int maxPrice) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct(minPrice, maxPrice);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}

	@GetMapping("{productId}") // http://localhost:9090/product/198
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {

		ResponseEntity<Product> responseEntity;
		if (productService.isProductExists(productId)) {
			product = productService.getProduct(productId);

			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK); // 201
		} else {
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 201
		}
		return responseEntity;
	}

	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer pId) {
		ResponseEntity<String> responseEntity;
		if (productService.isProductExists(pId)) {
			productService.deleteProduct(pId);
			responseEntity = new ResponseEntity<String>("Product deletion success", HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Product doesn't exist to delete", HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@PutMapping // http://localhost:9090/product/ - PUT - BODY
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		if (productService.isProductExists(product.getProductId())) {
			message = productService.updateProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Product not available , we cannot update",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
