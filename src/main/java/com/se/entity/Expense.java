package com.se.entity;

import java.sql.Date;

import lombok.Data;

/**
 * Expense
 * 
 * @author ASUS
 *
 */
@Data
public class Expense {

	int id;
	Date edate;
	String name;
	int amount;
}
