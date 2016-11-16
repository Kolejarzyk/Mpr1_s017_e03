package domain.model;

import java.util.List;

public class Account {
	private Person person;
	private double amount;
	private String currency;
	private List<HistoryLog> history;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<HistoryLog> getHistory() {
		return history;
	}
	public void setHistory(List<HistoryLog> history) {
		this.history = history;
	}
	
}