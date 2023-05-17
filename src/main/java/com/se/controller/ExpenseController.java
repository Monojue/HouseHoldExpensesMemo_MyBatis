package com.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.se.form.DailyExpenseForm;
import com.se.service.ExpenseService;

import jakarta.validation.Valid;

@Controller
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	/**
	 * Handler for the home page.
	 *
	 * @param model the model object
	 * @return the name of the home view template
	 */
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("list", this.expenseService.getMontlyList());
		return "home";
	}

	/**
	 * Handler for the add expense page.
	 *
	 * @param model the model object
	 * @return the name of the inputExpense view template
	 */
	@GetMapping("/add")
	public String addExpense(Model model) {
		model.addAttribute("form", new DailyExpenseForm());
		model.addAttribute("add", true);
		return "inputExpense";
	}

	/**
	 * Handler for adding an expense.
	 *
	 * @param e      the DailyExpenseForm object containing the expense data
	 * @param result the BindingResult object for validation errors
	 * @return the name of the view template to render
	 */
	@PostMapping("/add")
	public String addExpense(@Valid @ModelAttribute("form") DailyExpenseForm e, BindingResult result) {
		if (result.hasErrors()) {
			return "inputExpense";
		}
		this.expenseService.addDailyExpense(e);
		return "redirect:/";
	}

	/**
	 * Handler for updating an expense.
	 *
	 * @param model the model object
	 * @param year  the year value from the path variable
	 * @param month the month value from the path variable
	 * @param id    the id value from the path variable
	 * @return the name of the inputExpense view template
	 */
	@GetMapping("/detail/{year}/{month}/update/{id}")
	public String updateExpense(Model model, @PathVariable String year, @PathVariable String month,
			@PathVariable Integer id) {
		model.addAttribute("form", this.expenseService.getDailyExpenseById(id));
		model.addAttribute("month", year.concat("/").concat(month));
		model.addAttribute("add", false);
		return "inputExpense";
	}

	/**
	 * Handler for updating an expense.
	 *
	 * @param year   the year value from the path variable
	 * @param month  the month value from the path variable
	 * @param e      the DailyExpenseForm object containing the expense data
	 * @param result the BindingResult object for validation errors
	 * @return the name of the view template to render
	 */
	@PostMapping("/update/{year}/{month}")
	public String updateExpense(@PathVariable String year, @PathVariable String month,
			@Valid @ModelAttribute("form") DailyExpenseForm e, BindingResult result) {
		if (result.hasErrors()) {
			return "inputExpense";
		}
		this.expenseService.updateDailyExense(e);
		return "redirect:/detail/" + year.concat("/").concat(month);
	}

	/**
	 * Handler for displaying the detailed expenses for a specific year and month.
	 *
	 * @param model the model object
	 * @param year  the year value from the path variable
	 * @param month the month value from the path variable
	 * @return the name of the detailExpense view template
	 */
	@GetMapping("/detail/{year}/{month}")
	public String detailExpense(Model model, @PathVariable String year, @PathVariable String month) {
		model.addAttribute("list", this.expenseService.getDailyList(year + "-" + month));
		model.addAttribute("total", this.expenseService.getMonthlyListByMonth(year + "-" + month));
		model.addAttribute("base", year.concat("/").concat(month));
		return "detailExpense";
	}

	/**
	 * Handler for deleting an expense.
	 *
	 * @param id the id value from the path variable
	 * @return the name of the view template to redirect to
	 */
	@GetMapping("/delete/{id}")
	public String deleteExpense(@PathVariable int id) {
		this.expenseService.deleteDailyExpense(id);
		return "redirect:/";
	}

}
