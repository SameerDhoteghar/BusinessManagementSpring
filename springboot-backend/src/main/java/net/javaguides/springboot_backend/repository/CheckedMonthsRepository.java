package net.javaguides.springboot_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot_backend.model.LoginUser;
import net.javaguides.springboot_backend.model.Product;
import net.javaguides.springboot_backend.model.ProductPaymentMonths;

@Repository
public interface CheckedMonthsRepository extends JpaRepository<ProductPaymentMonths, Long> {

	List<ProductPaymentMonths> findAllByUserIdAndProductId(LoginUser userId,Product productId);
	
	@Transactional
	void deleteAllByProductIdAndUserId(Product productId, LoginUser userId);
}
