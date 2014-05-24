package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.Food;
import model.Order;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderDaoImpl implements OrderDao {
	private List<Order> orders;

	@Inject
	public OrderDaoImpl(FoodDao foodDao) {
		orders = new ArrayList<>();

		List<Food> foodsFor0 = new ArrayList<>();
		foodsFor0.add(foodDao.getById(0));
		orders.add(new Order(0, foodsFor0));

		List<Food> foodsFor1 = new ArrayList<>();
		foodsFor1.add(foodDao.getById(0));
		foodsFor1.add(foodDao.getById(2));
		orders.add(new Order(1, foodsFor1));

		List<Food> foodsFor2 = new ArrayList<>();
		foodsFor2.add(foodDao.getById(1));
		foodsFor2.add(foodDao.getById(2));
		orders.add(new Order(2, foodsFor2));
	}

	@Override
	public List<Order> listOrders() {
		return orders;
	}

	@Override
	public Order getById(int id) {
		for (Order order : orders) {
			if (order.id == id) {
				return order;
			}
		}
		return null;
	}

	@Override
	public void remove(int id) {
		for (Order order : orders) {
			if (order.id == id) {
				orders.remove(order);
				return;
			}
		}
	}

	@Override
	public int add(Order order) {
		if (order.id <= 0) {
			order.id = getNextId();
		}
		orders.add(order);
		return order.id;
	}

	private int getNextId() {
		int maxId = 0;
		for (Order order : orders) {
			if (order.id > maxId) {
				maxId = order.id;
			}
		}
		return maxId + 1;
	}
}
