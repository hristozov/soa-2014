package model;

import dao.FoodDao;
import dao.OrderDao;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class OrderAddRequest {
	public List<Integer> contents;

	public Order getOrder(FoodDao dao) {
		Order result = new Order();
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
		return result;
	}
}
