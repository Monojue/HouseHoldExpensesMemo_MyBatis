package com.se.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class DailyExpense {

	int id;
	Date edate;
	String name;
	int amount;
}
