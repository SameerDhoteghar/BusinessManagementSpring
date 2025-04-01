package net.javaguides.springboot_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot_backend.exception.ResourceNotFoundException;
import net.javaguides.springboot_backend.model.Employee;
import net.javaguides.springboot_backend.model.LoginUser;
import net.javaguides.springboot_backend.model.Product;
import net.javaguides.springboot_backend.model.ProductPaymentMonths;
import net.javaguides.springboot_backend.repository.CheckedMonthsRepository;
import net.javaguides.springboot_backend.repository.EmployeeRepository;
import net.javaguides.springboot_backend.repository.ProductRepository;
import net.javaguides.springboot_backend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CheckedMonths {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	@Autowired
	public ProductRepository productRepository;
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public CheckedMonthsRepository checkedMonthsRepository;
	
	@Transactional
	@PostMapping("/products/{productId}/{userId}/save-checked-months")
	public ResponseEntity<String> productSaveCheckedBoxes(@PathVariable String userId,@PathVariable long productId, @RequestBody Map<String, List<String>> payload) {
		System.out.println("In productSaveCheckedBoxes SpringBoot : "+ productId+" :: "+userId);
		
		Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
		LoginUser loginUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+userId));
		
		// Delete all existing records for the given productId inside a transaction
		checkedMonthsRepository.deleteAllByProductIdAndUserId(product, loginUser);
		
		List<String> selectedMonths = payload.get("selectedMonths");
		for (String month : selectedMonths) {
			ProductPaymentMonths paymentMonth = new ProductPaymentMonths();
            paymentMonth.setProductId(product);
            paymentMonth.setMonthName(month);
            paymentMonth.setUserId(loginUser);
            checkedMonthsRepository.save(paymentMonth);
        }
		
		return ResponseEntity.ok("Checked months saved successfully.");
	}
	
	@GetMapping("/products/{productId}/{userId}/checked-months")
	public ResponseEntity<List<String>> getCheckedMonths(@PathVariable Long productId,@PathVariable String userId) {
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
	    
	    LoginUser loginUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+userId));
	    
	    List<String> checkedMonths = checkedMonthsRepository.findAllByUserIdAndProductId(loginUser,product)
	            .stream()
	            .map(ProductPaymentMonths::getMonthName)
	            .collect(Collectors.toList());

	    return ResponseEntity.ok(checkedMonths);
	}

}
