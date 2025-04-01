package net.javaguides.springboot_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot_backend.model.Employee;
import net.javaguides.springboot_backend.model.LoginUser;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findAllByUserId(LoginUser userId);

}
