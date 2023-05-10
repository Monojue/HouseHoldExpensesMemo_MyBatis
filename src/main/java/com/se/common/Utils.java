package com.se.common;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.se.entity.DailyExpense;
import com.se.entity.MonthlyExpense;
import com.se.form.DailyExpenseForm;
import com.se.form.MontlyExpenseForm;

@Component
public class Utils {

	// change List of Daily Expense Entity to List of Daily Expense Form
	public List<DailyExpenseForm> changeDailyEntityListToForm(List<DailyExpense> deList) {
		List<DailyExpenseForm> defList = new ArrayList<>();
		for (DailyExpense d : deList) {
			DailyExpenseForm def = new DailyExpenseForm();
			def.setId(d.getId());
			def.setName(d.getName());
			def.setEdate(d.getEdate().toString().replaceAll("-", "/"));
			def.setAmount(addCommaToMoney(d.getAmount()));
			defList.add(def);
		}
		return defList;
	}

	// change Daily Expense Entity to Daily Expense Form
	public DailyExpenseForm changeDailyEntityToForm(DailyExpense de) {
		DailyExpenseForm def = new DailyExpenseForm();
		def.setId(de.getId());
		def.setName(de.getName());
		def.setEdate(de.getEdate().toString());
		def.setAmount(String.valueOf(de.getAmount()));
		return def;
	}

	// change Daily Expense Form to Daily Expense
	public DailyExpense changeDailyFormToEntity(DailyExpenseForm def) {
		DailyExpense de = new DailyExpense();
		de.setId(def.getId());
		de.setName(def.getName());
		Date sqlDate = Date.valueOf(def.getEdate());
		de.setEdate(sqlDate);
		de.setAmount(Integer.parseInt(def.getAmount()));
		return de;
	}

	// change List of Monthly Expense Entity to Monthly Expense Form
	public List<MontlyExpenseForm> changeMontlyEntityListToForm(List<MonthlyExpense> meList) {
		List<MontlyExpenseForm> mefList = new ArrayList<>();
		for (MonthlyExpense m : meList) {
			mefList.add(changeMontlyEntityToForm(m));
		}
		return mefList;
	}

	// change Monthly Expense Entity to Monthly Expense Form
	public MontlyExpenseForm changeMontlyEntityToForm(MonthlyExpense me) {
		MontlyExpenseForm mef = new MontlyExpenseForm();
		mef.setEdate(me.getEdate().toString().replaceAll("-", "/"));
		mef.setTotal(addCommaToMoney(me.getTotal()));
		return mef;
	}

	// money comma separator using Currency Format
//	public String addCommaToMoney(Integer amount) {
//		NumberFormat japanFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);
//		return japanFormat.format(new BigDecimal(amount));
//	}

	// money comma separator using DecimalFormat
	public String addCommaToMoney(Integer amount) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		return formatter.format(amount).concat(" JPY");
	}

}
