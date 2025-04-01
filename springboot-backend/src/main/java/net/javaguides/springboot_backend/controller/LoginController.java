package net.javaguides.springboot_backend.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot_backend.model.LoginUser;
import net.javaguides.springboot_backend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class LoginController {
	
	@Autowired  
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginUser userData){
		System.out.println("LoginUser method0: "+userData.getUserId()+ ","+userData.getPassword());
		//LoginUser loginUser = userRepository.getByUserId(userData.getUserId());
		LoginUser loginUser = userRepository.getByUserId(userData.getUserId());
		System.out.println("loginUser Object: "+loginUser);
		//System.out.println("LoginUser method: "+loginUser.getUserId()+ ","+loginUser.getPassword());
		if(loginUser != null){
			if(loginUser.getPassword().equals(userData.getPassword())){
				return ResponseEntity.ok(loginUser);
			}else {
				return ResponseEntity.ok(Collections.emptyList());
			}
		}else{
			return ResponseEntity.ok(Collections.emptyList());
		}
		//return (ResponseEntity<?>) ResponseEntity.internalServerError() ;	
	}
}
