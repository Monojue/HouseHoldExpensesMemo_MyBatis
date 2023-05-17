package com.se.common;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.se.dto.MonthlyExpenseDto;
import com.se.entity.Expense;
import com.se.form.DailyExpenseForm;
import com.se.form.MontlyExpenseForm;

/**
 * Utility class containing various helper methods for expense-related operations.
 */
@Component
public class Utils {

    /**
     * Converts a list of Expense entities to a list of DailyExpenseForm objects.
     *
     * @param deList the list of Expense entities
     * @return the list of DailyExpenseForm objects
     */
    public List<DailyExpenseForm> changeDailyEntityListToForm(List<Expense> deList) {
        List<DailyExpenseForm> defList = new ArrayList<>();
        for (Expense d : deList) {
            DailyExpenseForm def = new DailyExpenseForm();
            def.setId(d.getId());
            def.setName(d.getName());
            def.setEdate(d.getEdate().toString().replaceAll("-", "/"));
            def.setAmount(addCommaToMoney(d.getAmount()));
            defList.add(def);
        }
        return defList;
    }

    /**
     * Converts a Expense entity to a DailyExpenseForm object.
     *
     * @param de the Expense entity
     * @return the DailyExpenseForm object
     */
    public DailyExpenseForm changeDailyEntityToForm(Expense de) {
        DailyExpenseForm def = new DailyExpenseForm();
        def.setId(de.getId());
        def.setName(de.getName());
        def.setEdate(de.getEdate().toString());
        def.setAmount(String.valueOf(de.getAmount()));
        return def;
    }

    /**
     * Converts a DailyExpenseForm object to a Expense entity.
     *
     * @param def the DailyExpenseForm object
     * @return the Expense entity
     */
    public Expense changeDailyFormToEntity(DailyExpenseForm def) {
        Expense de = new Expense();
        de.setId(def.getId());
        de.setName(def.getName());
        Date sqlDate = Date.valueOf(def.getEdate());
        de.setEdate(sqlDate);
        de.setAmount(Integer.parseInt(def.getAmount()));
        return de;
    }

    /**
     * Converts a list of MonthlyExpenseDto entities to a list of MontlyExpenseForm objects.
     *
     * @param meList the list of MonthlyExpenseDto entities
     * @return the list of MontlyExpenseForm objects
     */
    public List<MontlyExpenseForm> changeMontlyEntityListToForm(List<MonthlyExpenseDto> meList) {
        List<MontlyExpenseForm> mefList = new ArrayList<>();
        for (MonthlyExpenseDto m : meList) {
            mefList.add(changeMontlyEntityToForm(m));
        }
        return mefList;
    }

    /**
     * Converts a MonthlyExpenseDto entity to a MontlyExpenseForm object.
     *
     * @param me the MonthlyExpenseDto entity
     * @return the MontlyExpenseForm object
     */
    public MontlyExpenseForm changeMontlyEntityToForm(MonthlyExpenseDto me) {
        MontlyExpenseForm mef = new MontlyExpenseForm();
        mef.setEdate(me.getEdate().toString().replaceAll("-", "/"));
        mef.setTotal(addCommaToMoney(me.getTotal()));
        return mef;
    }
    
//  money comma separator using Currency Format
// 	public String addCommaToMoney(Integer amount) {
// 		NumberFormat japanFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);
// 		return japanFormat.format(new BigDecimal(amount));
// 	}

    /**
     * Adds comma separators to a given amount to represent currency.
     *
     * @param amount the amount to format
     * @return the formatted amount with comma separators
     */
    public String addCommaToMoney(Integer amount) {
        DecimalFormat formatter = new DecimalFormat("###,###");
		return formatter.format(amount).concat(" JPY");
	}

}
