package service;

import com.google.inject.Inject;
import dao.CurrencyApiFacade;
import dao.FoodDao;
import dao.OrderDao;
import dao.UserDao;
import model.Order;
import model.OrderAddRequest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@Path("order")
@WebService
public class OrderService {
	@Inject
	public OrderDao orderDao;

	@Inject
	public FoodDao foodDao;

	@Inject
	public CurrencyApiFacade currencyApiFacade;

	@Inject
	public UserDao userDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public List<Order> list() {
		return orderDao.listOrders();
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public Order getById(@PathParam("id") @WebParam(name = "id") int id) {
		return orderDao.getById(id);
	}

	@Path("{id}")
	@DELETE
	@WebMethod
	public void remove(@PathParam("id") @WebParam(name = "id") int id) {
		orderDao.remove(id);
	}

	@POST
	@WebMethod
	public void addNew(@WebParam(name = "order") OrderAddRequest order) {
		orderDao.add(order.getOrder(foodDao, userDao));
	}

	@Path("{id}/confirm")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public void confirmOrder(@PathParam("id") @WebParam(name = "id") int id) {
		orderDao.confirm(id);
	}

	@Path("{id}/priceInUsd")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@WebMethod
	public String getOrderPriceInUsd(@PathParam("id") @WebParam(name = "id") int id) {
		BigDecimal currentRate = currencyApiFacade.getCurrentRate();
		Order order = orderDao.getById(id);
		return currentRate
				.multiply(BigDecimal.valueOf(order.getPrice()))
				.setScale(4, BigDecimal.ROUND_HALF_UP)
				.toString();
	}
}
