package com.acme.reference.impl.constants;

public class Constants {

	public static final String INSERT_STOCK = "INSERT INTO STOCK (STOCK_ID, STOCK_CODE, STOCK_NAME) VALUES (?, ?, ?)";
	public static final String READ_STOCK = "SELECT STOCK_ID, STOCK_CODE, STOCK_NAME FROM STOCK WHERE STOCK_ID = ?";
	public static final String UPDATE_STOCK = "UPDATE STOCK SET STOCK_CODE = ?, STOCK_NAME = ? WHERE STOCK_ID = ?";
	public static final String DELETE_STOCK = "DELETE FROM STOCK WHERE STOCK_ID = ?";

}