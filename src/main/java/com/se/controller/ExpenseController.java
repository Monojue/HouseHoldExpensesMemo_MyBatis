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
	
	String year;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("list", this.expenseService.getMontlyList());
		return "home";
	}

	@GetMapping("/add")
	public String addExpense(Model model) {
		model.addAttribute("form", new DailyExpenseForm());
		model.addAttribute("add", true);
		return "inputExpense";
	}

	@PostMapping("/add")
	public String addExpense(@Valid @ModelAttribute("form") DailyExpenseForm e, BindingResult result) {
		if (result.hasErrors()) {
			return "inputExpense";
		}
		this.expenseService.addDailyExpense(e);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String updateExpense(Model model, @PathVariable Integer id) {
		model.addAttribute("form", this.expenseService.getDailyExpenseById(id));
		model.addAttribute("month", this.year);
		model.addAttribute("add", false);
		return "inputExpense";
	}

	@PostMapping("/update")
	public String updateExpense(@Valid @ModelAttribute("form") DailyExpenseForm e, BindingResult result) {
		if (result.hasErrors()) {
			return "inputExpense";
		}
		this.expenseService.updateDailyExense(e);
		return "redirect:/";
	}

	@GetMapping("/detail/{year}/{month}")
	public String detailExpense(Model model, @PathVariable String year, @PathVariable String month) {
		this.year = year +"/"+ month;
		model.addAttribute("list", this.expenseService.getDailyList(year + "-" + month));
		model.addAttribute("total", this.expenseService.getMonthlyListByMonth(year + "-" + month));
		return "detailExpense";
	}

	@GetMapping("/delete/{id}")
	public String deleteExpense(@PathVariable int id) {
		this.expenseService.deleteDailyExpense(id);
		return "redirect:/";
	}

}
