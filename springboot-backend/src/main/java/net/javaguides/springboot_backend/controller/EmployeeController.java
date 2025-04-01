package net.javaguides.springboot_backend.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import net.javaguides.springboot_backend.exception.ResourceNotFoundException;
import net.javaguides.springboot_backend.model.Employee;
import net.javaguides.springboot_backend.model.LoginUser;
import net.javaguides.springboot_backend.repository.EmployeeRepository;
import net.javaguides.springboot_backend.repository.ProductRepository;
import net.javaguides.springboot_backend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	public EmployeeRepository employeeRepository;
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public ProductRepository productRepository;
	
	@GetMapping("/employees/{userId}/get-all-employees")
	public List<Employee> getAllEmployees(@PathVariable String userId){
		System.out.println("GetAllEmployees userId : "+userId);
		LoginUser loginUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+userId));
		return employeeRepository.findAllByUserId(loginUser);
	}
	
	@PostMapping("/employees/{userId}/create-new-employee")
	public Employee createEmployee(@PathVariable String userId,@RequestBody Employee employee) {
		System.out.println("PostEmployee userId : "+userId);
		LoginUser loginUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+userId));
		employee.setUserId(loginUser);
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmlployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id , @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+id));
		
		employee.setfirstName(employeeDetails.getfirstName());
		employee.setlastName(employeeDetails.getlastName());
		employee.setemailId(employeeDetails.getemailId());
		employee.setcontactNo(employeeDetails.getcontactNo());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@Transactional
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		System.out.println("indeleteEmployeeMethod: "+id);
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: "+id));
		System.out.println("EmployeeToDelete: "+employee.getfirstName());
		
		// First, delete all products associated with this employee
	    productRepository.deleteByEmployee(employee);
		
		//deleting employee
		employeeRepository.delete(employee);
		
		Map<String,Boolean> response = new HashMap<String,Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.stereotype.Service;
//
//	@Service
//	public class EmployeeService {
//
//	    @Autowired
//	    private EmployeeRepository employeeRepository;
//
//	    @Autowired
//	    private DepartmentRepository departmentRepository;
//
//	    public void addEmployee() {
//	        // Create and save a department
//	        Department dept = new Department();
//	        dept.setName("IT");
//	        departmentRepository.save(dept);
//
//	        // Create and save an employee
//	        Employee emp = new Employee();
//	        emp.setName("John Doe");
//	        emp.setDepartment(dept); // Assign department
//	        employeeRepository.save(emp);
//	    }
//	}

}
