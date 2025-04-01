package net.javaguides.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot_backend.model.LoginUser;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, String> {

	LoginUser getByUserId(String userId);

}
