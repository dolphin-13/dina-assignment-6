package com.coderscampus;

import java.time.YearMonth;

public class TeslaInfo {
	private YearMonth date;
	private Integer sales;

	public TeslaInfo(YearMonth date, Integer sales) {
		this.date = date;
		this.sales = sales;
	}

	public YearMonth getDate() {
		return date;
	}

	public void YearMonth(YearMonth date) {
		this.date = date;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "TeslaInfo [date=" + date + ", sales=" + sales + "]";
	}

}
