package service;

import com.google.inject.Inject;
import dao.FoodDao;
import dao.OrderDao;
import model.Order;
import model.OrderAddRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("order")
public class OrderService {
	@Inject
	public OrderDao orderDao;

	@Inject
	public FoodDao foodDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> list() {
		return orderDao.listOrders();
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Order getById(@PathParam("id") int id) {
		return orderDao.getById(id);
	}

	@Path("{id}")
	@DELETE
	public void remove(@PathParam("id") int id) {
		orderDao.remove(id);
	}

	@POST
	public void addNew(OrderAddRequest order) {
		orderDao.add(order.getOrder(foodDao));
	}
}
