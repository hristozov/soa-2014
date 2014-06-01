package model;

import dao.FoodDao;
import dao.MenuDao;
import dao.UserDao;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class OrderAddRequest {
	public Integer menu = -1;
	public final int user = -1;

	public Order getOrder(MenuDao menuDao, UserDao userDao) {
		Order result = new Order();
		User user = userDao.getById(this.user);
		List<Food> contents = new ArrayList<>();
		Menu targetMenu = menuDao.getById(menu);
		if (targetMenu != null) {
			for (Food food : targetMenu.contents) {
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
