package net.javaguides.springboot_backend.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import net.javaguides.springboot_backend.exception.ResourceNotFoundException;
import net.javaguides.springboot_backend.model.Employee;
import net.javaguides.springboot_backend.model.LoginUser;
import net.javaguides.springboot_backend.model.Product;
import net.javaguides.springboot_backend.repository.EmployeeRepository;
import net.javaguides.springboot_backend.repository.ProductRepository;
import net.javaguides.springboot_backend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class ProductController {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	@Autowired
	public ProductRepository productRepository;
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		System.out.println("inAllProducts");
		return productRepository.findAll();
	}
	
	@PostMapping("/products/employee/{userId}/create-new-product")
	public Product createProduct(@PathVariable String userId,@RequestBody Product product) {
		System.out.println("In createProduct SpringBoot : "+ product+" :: "+userId);
		LoginUser loginUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+userId));
		product.setUserId(loginUser);
		return productRepository.save(product);
	}
	
	@GetMapping("/products/employee/{employeeId}")
	public ResponseEntity<List<Product>> getProductsByEmployeeId(@PathVariable Long employeeId) {
		System.out.println("IN getProductsByEmployeeId : "+employeeId);
	    Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

	    List<Product> products = productRepository.findAllByEmployee(employee);
	    if (products.isEmpty()) {
	    	return ResponseEntity.ok(Collections.emptyList()); // ✅ Return an empty list instead of 404
	        //throw new ResourceNotFoundException("No products found for employeeId: " + employeeId);
	    }
	    return ResponseEntity.ok(products);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id){
		System.out.println("indeleteProductMethod: "+id);
		
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+id));
		System.out.println("ProductToDelete: "+product.getProduct());
		
		// delete particular product
	    productRepository.delete(product);
		
		Map<String,Boolean> response = new HashMap<String,Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/products/{id}/{userId}")
	public ResponseEntity<Product> updateProductAmountPaid(@PathVariable Long id ,@PathVariable String userId, @RequestBody long productAmountPaid){
		System.out.println("updateProductAmountPaid: "+id+ " :: "+ userId);
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+id));
		
		product.setAmountPaid(productAmountPaid);
		
		Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@GetMapping("/products/{productDueDate}/{userId}/products-by-dueDate")
	public ResponseEntity<List<Product>> getProductsByDueDate(@PathVariable String productDueDate,@PathVariable String userId) {
		System.out.println("IN getProductsByEmployeeId : "+productDueDate+ " userId: "+userId );
		LoginUser loginUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+userId));

		List<Product> products = productRepository.findAllByPaymentDueDateAndUserId(productDueDate,loginUser);
	    if (products.isEmpty()) {
	    	return ResponseEntity.ok(Collections.emptyList()); // ✅ Return an empty list instead of 404
	        //throw new ResourceNotFoundException("No products found for employeeId: " + employeeId);
	    }
	    return ResponseEntity.ok(products);
	}
}
