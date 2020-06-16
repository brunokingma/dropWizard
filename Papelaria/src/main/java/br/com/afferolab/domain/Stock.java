package br.com.afferolab.domain;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class Stock {
	
	public UUID id;
	public int barcode;
	public String name;
	public String description;
	public String category;
	public int quantity;
	
	
	public Stock() {
		super();
		this.barcode = new Random().ints(1, 0, 11).findFirst().getAsInt();
		this.id = UUID.randomUUID(); 
	}

	
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
}
