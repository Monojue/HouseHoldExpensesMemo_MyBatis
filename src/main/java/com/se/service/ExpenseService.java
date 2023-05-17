package com.se.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.se.common.Utils;
import com.se.form.DailyExpenseForm;
import com.se.form.MontlyExpenseForm;
import com.se.mapper.ExpenseMapper;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    ExpenseMapper expenseMapper;

    @Autowired
    Utils utils;

    /**
     * Retrieves a list of daily expenses for a specific month.
     *
     * @param month The month to retrieve daily expenses for.
     * @return A list of DailyExpenseForm objects for the specified month.
     */
    public List<DailyExpenseForm> getDailyList(String month) {
        return utils.changeDailyEntityListToForm(this.expenseMapper.getDailyList(month));
    }

    /**
     * Retrieves a list of monthly expenses.
     *
     * @return A list of MontlyExpenseForm objects representing monthly expenses.
     */
    public List<MontlyExpenseForm> getMontlyList() {
        return utils.changeMontlyEntityListToForm(this.expenseMapper.getMontlyList());
    }

    /**
     * Retrieves the monthly expense for a specific month.
     *
     * @param month The month to retrieve the monthly expense for.
     * @return A MontlyExpenseForm object representing the monthly expense for the specified month.
     */
    public MontlyExpenseForm getMonthlyListByMonth(String month) {
        return utils.changeMontlyEntityToForm(this.expenseMapper.getMonthlyExpenseByMonth(month));
    }

    /**
     * Retrieves a daily expense by its ID.
     *
     * @param id The ID of the daily expense to retrieve.
     * @return A DailyExpenseForm object representing the daily expense with the specified ID.
     */
    public DailyExpenseForm getDailyExpenseById(Integer id) {
        return utils.changeDailyEntityToForm(this.expenseMapper.findById(id));
    }

    /**
     * Adds a new daily expense.
     *
     * @param ex The DailyExpenseForm object representing the daily expense to add.
     */
    public void addDailyExpense(DailyExpenseForm ex) {
        try {
            this.expenseMapper.insert(utils.changeDailyFormToEntity(ex));
        } catch (Exception e) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * Updates an existing daily expense.
     *
     * @param ex The DailyExpenseForm object representing the daily expense to update.
     */
    public void updateDailyExense(DailyExpenseForm ex) {
        try {
            this.expenseMapper.update(utils.changeDailyFormToEntity(ex));
        } catch (Exception e) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * Deletes a daily expense by its ID.
     *
     * @param id The ID of the daily expense to delete.
     */
    public void deleteDailyExpense(int id) {
        try {
            this.expenseMapper.delete(id);
        } catch (Exception e) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }

}
