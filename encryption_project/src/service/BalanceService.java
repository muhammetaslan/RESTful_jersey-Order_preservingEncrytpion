package service;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.BalanceDAOImple;


@Path("/webServiceBalance")
public class BalanceService {
	
	BalanceDAOImple balanceDAO = new BalanceDAOImple();

	@GET
	@Path("/{cardid}")
	public void updateBalanceWithWB(@javax.ws.rs.PathParam("cardid") String cardid) {
		
		balanceDAO.updateBalance(cardid);
		System.out.println("> "+ cardid + " web service update is work");
		
	}// end of updatBalanceWithWB
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String giveName() {
		return "aslan";
	}// end of GET method

}// end of class

