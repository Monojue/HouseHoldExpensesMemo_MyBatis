package com.se.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.se.common.Utils;
import com.se.form.DailyExpenseForm;
import com.se.form.MontlyExpenseForm;
import com.se.mapper.ExpenseMapper;

@Service
public class ExpenseService {

	@Autowired
	ExpenseMapper expenseMapper;

	@Autowired
	Utils utils;

	public List<DailyExpenseForm> getDailyList(String month) {
		return utils.changeDailyEntityListToForm(this.expenseMapper.getDailyList(month));
	}

	public List<MontlyExpenseForm> getMontlyList() {
		return utils.changeMontlyEntityListToForm(this.expenseMapper.getMontlyList());
	}

	public MontlyExpenseForm getMonthlyListByMonth(String month) {
		return utils.changeMontlyEntityToForm(this.expenseMapper.getMonthlyExpenseByMonth(month));
	}

	public DailyExpenseForm getDailyExpenseById(Integer id) {
		return utils.changeDailyEntityToForm(this.expenseMapper.findById(id));
	}

	public void addDailyExpense(DailyExpenseForm ex) {
		try {
			this.expenseMapper.insert(utils.changeDailyFormToEntity(ex));
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

	public void updateDailyExense(DailyExpenseForm ex) {
		try {
			this.expenseMapper.update(utils.changeDailyFormToEntity(ex));
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	public void deleteDailyExpense(int id) {
		try {
			this.expenseMapper.delete(id);
		}catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

}
