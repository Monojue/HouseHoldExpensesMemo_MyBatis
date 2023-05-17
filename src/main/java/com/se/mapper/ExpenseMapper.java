package com.se.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.se.dto.MonthlyExpenseDto;
import com.se.entity.Expense;

@Mapper
public interface ExpenseMapper {

    /**
     * Retrieves a list of daily expenses for a specific month.
     *
     * @param month The month to retrieve expenses for.
     * @return A list of Expense objects for the specified month.
     */
    public List<Expense> getDailyList(@Param("month") String month);

    /**
     * Retrieves a list of monthly expenses.
     *
     * @return A list of MonthlyExpenseDto objects representing monthly expenses.
     */
    public List<MonthlyExpenseDto> getMontlyList();

    /**
     * Retrieves the monthly expense for a specific month.
     *
     * @param month The month to retrieve the monthly expense for.
     * @return A MonthlyExpenseDto object representing the monthly expense for the specified month.
     */
    public MonthlyExpenseDto getMonthlyExpenseByMonth(@Param("month") String month);

    /**
     * Retrieves an expense by its ID.
     *
     * @param id The ID of the expense to retrieve.
     * @return An Expense object representing the expense with the specified ID.
     */
    public Expense findById(@Param("id") int id);

    /**
     * Inserts a new expense.
     *
     * @param e The Expense object to insert.
     */
    public void insert(Expense e);

    /**
     * Updates an existing expense.
     *
     * @param e The Expense object to update.
     */
    public void update(Expense e);

    /**
     * Deletes an expense.
     *
     * @param id The ID of the expense to delete.
     */
    public void delete(int id);
}
