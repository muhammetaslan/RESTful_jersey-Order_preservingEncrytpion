package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Balance {

	private int id;
	private String username;
	private String cardId;
	private int balance;

		
	
	// no arg consturactor
	public Balance() {
		
	}
	
	public Balance(int id, String username, String cardId, int balance) {
		super();
		this.id = id;
		this.username = username;
		this.cardId = cardId;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
