package com.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.exception.ResourceNotFoundException;
import com.rewards.mock.CustomerRewardsServiceMock;
import com.rewards.model.Customer;
import com.rewards.model.CustomerTransaction;
import com.rewards.service.CustomerRewardsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer Rewards API", description = "Spring Boot Api to calculate Customer Rewards")
@RestController
public class CustomerRewardsController {
	
	/*Mock CustomerService for testing
	 * @Autowired
	private CustomerRewardsServiceMock rewardsServiceMock;
	
	
	@GetMapping("/{idUser}/rewards")
	public List<CustomerTransaction> getAll(@PathVariable Integer idUser) {
		return rewardsServiceMock.getAll();
	}*/
	
	@Autowired
	private CustomerRewardsService rewardsService;

	@Operation(summary = "Get All Customer Details List")
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200", description = "Success"),
	  @ApiResponse(responseCode = "400", description = "Bad request"),
	  @ApiResponse(responseCode = "500", description = "Server Error")})
	@GetMapping(path = "/customers")
	public ResponseEntity<List<Customer>> findCustomerAll() {
		try {
			return new ResponseEntity<>(rewardsService.getCustomerAll(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Operation(summary = "Get Customer By Id")
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200", description = "Success"),
	  @ApiResponse(responseCode = "400", description = "Bad request"),
	  @ApiResponse(responseCode = "500", description = "Server Error")})
	@GetMapping(path = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
		Customer customer = rewardsService.getCustomerById(id);
		if (customer == null) {
			//return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("Not Customer Found with id = " + id);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}	

}
