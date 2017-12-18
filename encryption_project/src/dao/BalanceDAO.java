package dao;


import java.math.BigInteger;
import java.util.List;

import model.Balance;

public interface BalanceDAO {
	
	// add new user and card the database
	public void insertNewCard(String username,String cardId,int balance,String encryptedValue);

	// get the all value form database
	public List<Balance> getBalances(); 

	// update balanece information
	public void updateBalance(String cardId);
	
	// delete user from system
	public void deleteBalance(int id);
}
