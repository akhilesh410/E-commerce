package com.myapp.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myapp.productservice.entity.Product;
import com.myapp.productservice.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
     ProductService productService;
    
    @PostMapping( "/products")
    private ResponseEntity<Product> addProduct(@RequestBody Product product){
  
    		try {
    			productService.addProduct(product);
    	        return new ResponseEntity<Product>(
    	        		product,HttpStatus.CREATED);
    		}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
    		
}       
    }

    @GetMapping ( "/product")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products =  productService.getAllProduct();
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<Product>>(
        			products,HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);       
    }

    @GetMapping(value = "/products", params = "category")
    public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam ("category") String category){
        List<Product> products = productService.getAllProductByCategory(category);
        if(!products.isEmpty()) {
	return new ResponseEntity<List<Product>>(
        			products,HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping ( "/products/{id}")
    public ResponseEntity<Product> getOneProductById(@PathVariable ("id") long id){
        Product product =  productService.getProductById(id);
        if(product != null) {
        	return new ResponseEntity<Product>(
        			product,HttpStatus.OK);
        }
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/products", params = "name")
    public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam ("name") String name){
        List<Product> products =  productService.getAllProductsByName(name);
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
        			
        }
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }
}
