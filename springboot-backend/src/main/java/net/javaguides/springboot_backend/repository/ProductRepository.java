package net.javaguides.springboot_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import net.javaguides.springboot_backend.model.Employee;
import net.javaguides.springboot_backend.model.LoginUser;
import net.javaguides.springboot_backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByEmployee(Employee employee);
	
	@Modifying
	@Transactional
	void deleteByEmployee(Employee employee);
	
	List<Product> findAllByPaymentDueDateAndUserId(String productPaymentDueDate, LoginUser userId);
}
