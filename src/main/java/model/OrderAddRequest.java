package model;

import dao.FoodDao;
import dao.UserDao;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class OrderAddRequest {
	public List<Integer> contents;
	public final int user = -1;

	public Order getOrder(FoodDao dao, UserDao userDao) {
		Order result = new Order();
		User user = userDao.getById(this.user);
		List<Food> contents = new ArrayList<>();
		if (contents != null) {
			for (Integer foodId : this.contents) {
				Food food = dao.getById(foodId);
				if (food != null) {
					contents.add(food);
				}
			}
		}
		result.contents = contents;
		result.user = user;
		return result;
	}
}
