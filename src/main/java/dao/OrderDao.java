package dao;

import com.google.inject.ImplementedBy;
import model.Order;

import java.util.List;

@ImplementedBy(OrderDaoImpl.class)
public interface OrderDao {
	List<Order> listOrders();
	Order getById(int id);
	void remove(int id);
	int add(Order order);
	void confirm(int id);
}
