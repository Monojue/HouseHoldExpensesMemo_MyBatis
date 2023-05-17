package com.se.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) for monthly expenses.
 */
@Data
public class MonthlyExpenseDto {

	String edate;
	int total;
}
