package com.se.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.se.entity.DailyExpense;
import com.se.entity.MonthlyExpense;

@Mapper
public interface ExpenseMapper {

	public List<DailyExpense> getDailyList(@Param("month") String month);
	
	public List<MonthlyExpense> getMontlyList();
	
	public MonthlyExpense getMonthlyExpenseByMonth(@Param("month") String month);
	
	public DailyExpense findById(@Param("id") int id);
	
	public void insert(DailyExpense e);
	
	public void update(DailyExpense e);
	
	public void delete(int e);
}
