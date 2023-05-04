package com.se.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DailyExpenseForm {

	int id;
	@NotBlank(message = "日付を入力してください。")
	String edate;
	@NotBlank(message = "項目を入力してください。")
	String name;
	@NotBlank(message = "費用を入力してください。")
	@Positive(message = "費用をゼロ以上入力してください。")
	String amount;
}
