package com.example.cubesum.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StoreData {
	private @Id @GeneratedValue long id;
	private int numberTest;
	private int numberPoints;
	private int numberOperations;
	private String response;
	
	public StoreData() {
		this.numberTest = 0;
		this.numberPoints = 0;
		this.numberOperations = 0;
		this.response = "";
	}

	public int getNumberTest() {
		return numberTest;
	}

	public void setNumberTest(int numberTest) {
		this.numberTest = numberTest;
	}

	public int getNumberPoints() {
		return numberPoints;
	}

	public void setNumberPoints(int numberPoints) {
		this.numberPoints = numberPoints;
	}

	public int getNumberOperations() {
		return numberOperations;
	}

	public void setNumberOperations(int numberOperations) {
		this.numberOperations = numberOperations;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
