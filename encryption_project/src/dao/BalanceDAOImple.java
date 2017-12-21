package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Balance;
import operation.OPE;

public class BalanceDAOImple implements BalanceDAO {

	private static final String INSERT_QUERY= "insert into balance(username,cardId,balance,encryptedValue) values (?,?,?,?)";
	private static final String SELECT_QUERY= "SELECT * FROM balance";
	private static final String DELETE_QUERY= "DELETE FROM balance where id = ?";
	private static final String GET_MAX_QUERY = "SELECT MAX(encryptedValue) AS LargestBalance FROM balance";
	private static final String GET_MIN_QUERY = "SELECT MIN(encryptedValue) AS LowestBalance FROM balance";
	private static final String GET_GREATER_BALANCE = "SELECT * FROM balance WHERE encryptedValue < ?";
	private static final String GET_LOWEST_BALANCE = "SELECT * FROM balance WHERE encryptedValue > ?";
	private static final String GET_EQUAL_BALANCE = "SELECT * FROM balance WHERE encryptedValue = ?";
	private static final String GET_EQUAL_BALANCE_WITH_cardid = "SELECT encryptedValue FROM balance WHERE cardId = ?";
	private static final String ORDER_BY = "SELECT * FROM balance ORDER BY encryptedValue ASC";
	
	private static final String getBy_cardId = "SELECT encryptedValue FROM balance WHERE cardId = ?";
	private static final String Update_Query = "UPDATE balance SET encryptedValue = ?  WHERE cardId = ?";

	public BalanceDAOImple() {
		// TODO Auto-generated constructor stub
	}


	/*
	 * connetion the database with manager object
	 * 
	 * */
	private DataSource getDataSource(){

		ConnectionManager manager = new ConnectionManager();
		DataSource dataSource = manager.getMySqlDatasource();
		return dataSource;
	}


	/*
	 * Insert the balance value into the databese table
	 * 
	 * */
	@Override
	public void insertNewCard(String username,String cardId ,int balance,String encryptedValue) {

		DataSource datasource = getDataSource();

		try {

			Connection conn = datasource.getConnection();
			PreparedStatement pre = conn.prepareStatement(INSERT_QUERY);


			pre.setString(1, username);
			pre.setString(2, cardId);
			pre.setInt(3, balance);
			pre.setString(4, encryptedValue);
			pre.executeUpdate();

			System.out.println("Record is inserted into table!");

			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end od the catch
	}// end of the ýnsert method	


	@Override
	public List<Balance> getBalances() {

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceList = new ArrayList<>();

		try {

			Connection conn = datasource.getConnection();
			PreparedStatement pre = conn.prepareStatement(SELECT_QUERY);
			ResultSet result = pre.executeQuery();


			while(result.next()) {

				int id = result.getInt(1);
				String username = result.getString(2);
				String cardId = result.getString(3);
				int balace = result.getInt(4);

				Balance balance = new Balance(id,username,cardId,balace);
				balanceList.add(balance);
			}// end of while
			System.out.println("ok 200");
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}//end of catch


		return balanceList;
	}// end of getBalance

	

	@Override
	public void deleteBalance(int id) {

		DataSource source = getDataSource();

		try {

			Connection conn = source.getConnection();
			PreparedStatement pre = conn.prepareStatement(DELETE_QUERY);

			pre.setInt(1, id);
			pre.execute();
			pre.close();
			conn.close();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		}// end of catch


	}// end of delete method

	/*
	 * this method update the balance status according to card ýd value
	 * */
	@Override
	public void updateBalance(String cardID) {
		
		OPE o = new OPE();
		
		DataSource dataSource = getDataSource();
		
		try {
			
			int control_query; 
			Connection connection = dataSource.getConnection();
			PreparedStatement pre = connection.prepareStatement(Update_Query);
			
			
			PreparedStatement pre1 = connection.prepareStatement(GET_EQUAL_BALANCE_WITH_cardid);
			pre1.setString(1, cardID);
			ResultSet result = pre1.executeQuery();
			
			
			String enc = null;
			while(result.next()) {
				
				enc = result.getString(1);
			
			}
			System.out.println("Encrtped value : " + enc);
			
			BigInteger dec_result = new BigInteger(enc);
			dec_result = o.decrypt(dec_result);
			
			System.out.println("Decrypted value : "+ dec_result);
			
			int dec_resultInteger = dec_result.intValue();
			
			dec_resultInteger -= 350;
			System.out.println("New value of balance : " + dec_resultInteger);
			enc = Integer.toString(dec_resultInteger);
			
			BigInteger newEncryptedValue = new BigInteger(enc);
			newEncryptedValue = o.encrypt(newEncryptedValue);
			
			pre.setString(1, newEncryptedValue.toString());
			pre.setString(2,cardID);
			control_query =  pre.executeUpdate();
			
			// controled the query work successfully or not
			if(control_query == 1) {
				System.out.println("Updating balance value worked succesfully");
			}//end of if
			
			pre.close();
			connection.close();
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end of try catch
		
		

	}// end of the updateBalance


	public List<Balance> getLargestBalance() {

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceListMax= new ArrayList<>();
		
		try {

			Connection connection= datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(GET_MAX_QUERY);
			ResultSet result = pre.executeQuery();
			
			result.next();
			
			
			String enc_value_max = result.getString(1);
			
			System.out.println(enc_value_max);
			
			pre.close();
			connection.close();
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end of try catch

		return balanceListMax;
	}// end of getLargestBalance


	public List<Balance> getLowestBalance() {

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceListMin= new ArrayList<>();

		try {

			Connection connection= datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(GET_MIN_QUERY);
			ResultSet result = pre.executeQuery();

			result.next();
			
			String enc_value_min = result.getString(1);
			
			System.out.println(enc_value_min);
			
			

		} catch (SQLException e) {

			System.out.println(e.getMessage() + ">");
			e.printStackTrace();
		}// end of try catch

		return balanceListMin;
	}// end of getLowestBalance


	public List<Balance> getGraterBalance(String value){

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceListGr = new ArrayList<>();

		try {

			Connection connection = datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(GET_GREATER_BALANCE);
			pre.setString(1, value);
			ResultSet result = pre.executeQuery();

			while(result.next()) {

				int id = result.getInt(1);
				String username = result.getString(2);
				String cardId = result.getString(3);
				int balance = result.getInt(4);
	
				Balance balance1 = new Balance(id, username, cardId, balance);
	
				balanceListGr.add(balance1);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end of catch

		return balanceListGr;
	}// end of getgeraterbalance()


	public List<Balance> getLowestBalance(String value){

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceListlw = new ArrayList<>();

		try {

			Connection connection = datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(GET_LOWEST_BALANCE);
			pre.setString(1, value);
			ResultSet result = pre.executeQuery();

			
			while(result.next()) {

				int id = result.getInt(1);
				String username = result.getString(2);
				String cardId = result.getString(3);
				int balance = result.getInt(4);
	
				Balance balance2 = new Balance(id, username, cardId, balance);
	
				balanceListlw.add(balance2);
			}
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end of catch

		return balanceListlw;
	}// end of getlowestbalance()
	
	
	public List<Balance> getEqualBalance(String value){

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceListeq = new ArrayList<>();

		try {

			Connection connection = datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(GET_EQUAL_BALANCE);
			pre.setString(1, value);
			ResultSet result = pre.executeQuery();
			
			
			while(result.next()) {
				
				int id = result.getInt(1);
				String username = result.getString(2);
				String cardId = result.getString(3);
				int balance = result.getInt(4);
	
				Balance balance3 = new Balance(id, username, cardId, balance);
	
				balanceListeq.add(balance3);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end of catch

		return balanceListeq;
	}// end of getequalbalance()

	
	
	public List<Balance> getOrderByBalance(int value){

		DataSource datasource = getDataSource();

		ArrayList<Balance> balanceListor = new ArrayList<>();

		try {

			Connection connection = datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(ORDER_BY);
			ResultSet result = pre.executeQuery();
			
			while(result.next()) {


				int id = result.getInt(1);
				String username = result.getString(2);
				String cardId = result.getString(3);
				int balance = result.getInt(4);
				
				Balance balance4 = new Balance(id, username, cardId, balance);
				balanceListor.add(balance4);

			}// end of while
			
			
			pre.close();
			connection.close();
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}// end of catch

		return balanceListor;
	}// end of getlowestbalance()
	
	
	public void updateBalanceWebService(int cardid) {
	
		DataSource datasource = getDataSource();
		
		try {
		
			Connection connection = datasource.getConnection();
			PreparedStatement pre = connection.prepareStatement(getBy_cardId);
			
			pre.setInt(1, cardid);
			pre.execute();
			pre.close();
			connection.close();
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
