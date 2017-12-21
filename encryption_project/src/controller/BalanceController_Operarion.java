package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.BalanceDAOImple;
import model.Balance;
import operation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/balanceController_op")
public class BalanceController_Operarion extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("balanceId"));

		BalanceDAOImple balanceDAO = new BalanceDAOImple();

		List<Balance> balanceList = null;

		if(id == 222) {	

			balanceList = balanceDAO.getLargestBalance();
		}
		else if(id == 333) {

			balanceList = balanceDAO.getLowestBalance();
		}
		else if(id == 444) {

			balanceList = balanceDAO.getOrderByBalance(id);
		}
		else {
			System.out.println("Unknow request");

			PrintWriter pw = resp.getWriter();

			pw.println("Unknow request");
		}// end of else

		req.setAttribute("allBalance_two", balanceList);

		RequestDispatcher dispatcher1 = req.getRequestDispatcher("balance.jsp");
		dispatcher1.forward(req, resp);

	}// end of GET 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		OPE o = new OPE();
		BalanceDAOImple balanceDAO = new BalanceDAOImple();

		String operation_type = req.getParameter("operation");
		int balance_range =Integer.parseInt(req.getParameter("balance"));

		List<Balance> balanceList = null;

		
		
		BigInteger new_p =  new BigInteger(Integer.toString(balance_range));
		BigInteger enc_temp = o.encrypt(new_p);
		
		System.out.println(operation_type + " " + balance_range + " -> " + " encrypted range value : " + enc_temp);
		
		
		

		if(operation_type.equals("bigger")) {	
			
			System.out.println("work bigger query");
			balanceList = balanceDAO.getGraterBalance(enc_temp.toString());
		}
		else if(operation_type.equals("smaller")) {
			
			System.out.println("work smaller query");
			balanceList = balanceDAO.getLowestBalance(enc_temp.toString());
			
		}
		else if(operation_type.equals("equal")) {
			System.out.println("work equal query");
			balanceList = balanceDAO.getEqualBalance(enc_temp.toString());
		}
		else {
			System.out.println("Unknow request");

			PrintWriter pw = resp.getWriter();

			pw.println("Unknow request");
		}// end of else

		req.setAttribute("allBalance_two", balanceList);

		RequestDispatcher dispatcher1 = req.getRequestDispatcher("operation_view.jsp");
		dispatcher1.forward(req, resp);
	}
}
