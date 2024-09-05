package com.rewards.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	@Schema(description = "Customer Id", type = "integer", example = "101")
	private Integer id;
	
	@Schema(description = "Customer Name", type = "String", example = "Ramesh")
	private String name;
	
	@Schema(description = "Customer Transaction Details", type = "Set", example = "{\n"
			+ "                \"points\": 50,\n"
			+ "                \"id\": 101,\n"
			+ "                \"total\": 100.0,\n"
			+ "                \"description\": \"Product 1\",\n"
			+ "                \"saveDate\": \"2024-09-04T16:54:00.000+0000\"\n"
			+ "            }")
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CustomerTransaction> transactions;
	
	@Schema(description = "Total Rewards Points Earned", type = "Long", example = "200.0")
	@JsonInclude
	@Transient
	private Long rewardPoints;
	
	@Schema(description = "Total Purchases done by customer", type = "Double", example = "300.0")
	@JsonInclude
	@Transient
	private Double totalPurchases;
	
	public Customer() {
		super();
	}
	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<CustomerTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<CustomerTransaction> transactions) {
		this.transactions = transactions;
	}
	public Long getRewardPoints() {
		if (transactions == null || transactions.isEmpty()) return 0l;
		
		return transactions.stream().map(x -> x.getPoints().intValue()).reduce(0, (a,b) -> a + b).longValue();
	}
	public Double getTotalPurchases() {
		if (transactions == null || transactions.isEmpty()) return 0d;
		
		return transactions.stream().map(x -> x.getTotal().doubleValue()).reduce(0d, (a,b) -> a + b).doubleValue();
	}
	
	
}
