package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.Food;
import model.Menu;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class MenuDaoImpl implements MenuDao {
	private List<Menu> menus = new ArrayList<>();

	@Inject
	public MenuDaoImpl(FoodDao foodDao) {
		List<Food> foods = foodDao.listFoods();

		List<Food> menu1 = new ArrayList<>();
		menu1.add(foods.get(0));
		menu1.add(foods.get(1));
		menus.add(new Menu(0, "Menu 1", "Shopska + Burger", menu1));

		List<Food> menu2 = new ArrayList<>();
		menu2.add(foods.get(1));
		menu2.add(foods.get(2));
		menus.add(new Menu(1, "Menu 2", "Burger + Duner", menu2));

		List<Food> menu3 = new ArrayList<>();
		menu3.add(foods.get(0));
		menu3.add(foods.get(2));
		menus.add(new Menu(2, "Menu 3", "Shopska + Duner", menu3));
	}

	@Override
	public List<Menu> listMenus() {
		return menus;
	}

	@Override
	public Menu getById(int id) {
		for (Menu menu : menus) {
			if (menu.id == id) {
				return menu;
			}
		}
		return null;
	}
}
