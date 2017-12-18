package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BalanceDAOImple;
import model.Balance;
import operation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/balanceController")
public class BalanceController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		int id = Integer.parseInt(req.getParameter("balanceId"));
		

		BalanceDAOImple balanceDAO = new BalanceDAOImple();

		balanceDAO.deleteBalance(id);

		

		List<Balance> balanceList = balanceDAO.getBalances();

		//req.setAttribute("allBalance_two",operationList);
		req.setAttribute("allBalance",balanceList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("balance.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		OPE o = new OPE();
		BalanceDAOImple balanceDAO = new BalanceDAOImple();

		String userName = req.getParameter("username");
		String cardId = req.getParameter("cardId");
		int balance = Integer.parseInt(req.getParameter("balance"));

		BigInteger encrytedValue;
		BigInteger decrytedValue;

		
		/*
		 * */
		
		
		
		BigInteger new_p =  new BigInteger(cardId);
		BigInteger enc_temp = o.encrypt(new_p);
		BigInteger dec_temp = o.decrypt(enc_temp);
		
		String cardID_enc = enc_temp.toString();
		System.out.println(cardID_enc + " stirng enc value<----> bigineteger enc value " + enc_temp + "--> big integer dec_temp" + dec_temp);
		
		/*
		 * */
		
		BigInteger p = new BigInteger("" + balance);

		encrytedValue = o.encrypt(p);

		decrytedValue = o.decrypt(encrytedValue);

		String enc_db_value = encrytedValue.toString();
		
		BigInteger enc_db_value_bg = new BigInteger(enc_db_value);
		
		enc_db_value_bg = o.decrypt(enc_db_value_bg);
		
		System.out.println( enc_db_value_bg +" dec <-----> enc "+ enc_db_value + " \n "+ encrytedValue + " enc <-----> dec" + decrytedValue);


		balanceDAO.insertNewCard(userName,cardId,balance,enc_db_value);

		// eklenen verileri burada getBalance () ile cekiyoruz
		List<Balance> balanceList = balanceDAO.getBalances();


		req.setAttribute("allBalance",balanceList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("balance.jsp");
		dispatcher.forward(req, resp);
	}
}
