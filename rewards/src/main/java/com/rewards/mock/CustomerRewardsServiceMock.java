package com.rewards.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rewards.model.Customer;
import com.rewards.model.CustomerTransaction;

@Service
public class CustomerRewardsServiceMock {

	private static List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
	private static long index;
	
	static {
		
		transactions.add( new CustomerTransaction(index++, new Customer(1, "Sia"), 100.0, "Product 1", new Date()) );
		transactions.add( new CustomerTransaction(index++, new Customer(2, "Ria"), 50.0, "Product 2", new Date()) );
		transactions.add( new CustomerTransaction(index++, new Customer(3, "Priya"), 120.0, "Product 3", new Date()) );
	
	}
	
	
	public List<CustomerTransaction> getAll() {
		return transactions;
	}
	
}
