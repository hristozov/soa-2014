package service;

import com.google.inject.Inject;
import dao.FoodDao;
import dao.OrderDao;
import model.Order;
import model.OrderAddRequest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("order")
@WebService
public class OrderService {
	@Inject
	public OrderDao orderDao;

	@Inject
	public FoodDao foodDao;

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
		orderDao.add(order.getOrder(foodDao));
	}

	@Path("{id}/confirm")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public void confirmOrder(@PathParam("id") @WebParam(name = "id") int id) {
		orderDao.confirm(id);
	}
}
