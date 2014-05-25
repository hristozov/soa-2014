package service;

import com.google.inject.Inject;
import dao.CurrencyApiFacade;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("currencyRate")
@WebService
public class CurrencyRateService {
	@Inject
	public CurrencyApiFacade currencyApiFacade;

	@Path("current")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@WebMethod
	public String getCurrentRate() {
		return currencyApiFacade.getCurrentRate().toString();
	}
}
