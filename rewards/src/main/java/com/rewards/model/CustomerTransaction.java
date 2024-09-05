package com.rewards.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class CustomerTransaction extends Reward{
	@Id
	@GeneratedValue
	@Schema(description = "Customer Id", type = "Long", example = "10001")
	private Long id;
	
	@Schema(description = "Customer", type = "Customer", example = "\"id\": 102,\n"
			+ "        \"name\": \"Prakash\",")
	@JsonIgnore
	@ManyToOne
	@JoinColumn //foreign key to customer
	private Customer customer;
	
	@Schema(description = "Total Amount", type = "Double", example = "100.0")
	private Double total;
	
	@Schema(description = "Product Description", type = "String", example = "Product 1")
	private String description;
	
	//@Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Transaction Date", type = "Date", example = "2024-09-04 22:24:000")
	private Date saveDate;
	
	
	public CustomerTransaction() {
		super();
	}
	public CustomerTransaction(Long id, Customer customer, Double total, String description, Date date) {
		super();
		this.id = id;
		this.customer = customer;
		this.total = total;
		this.description = description;
		this.saveDate = date;
	}

	public Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Long getPoints() {
		this.points = 0l;
		
		if (this.total > 50 && this.total <= 100) {
			this.points += (this.total.intValue() - 50) * 1;
			
		} 
		
		if (this.total > 100) {
			this.points += 50;  //1 point for every dollar spent over $50
			this.points += (this.total.intValue() - 100) * 2;  //2 points for every dollar spent over $100
		}
		
		return this.points;
	}
	
	@Override
	public String toString() {
		return String.format("CustomerTransaction [id=%s, customer=%s, total=%s, description=%s, saveDate=%s]", id, customer,
				total, description, saveDate);
	}
	
}
