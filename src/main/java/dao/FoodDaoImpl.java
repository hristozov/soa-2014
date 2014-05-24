package dao;

import model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
	private List<Food> foods;

	public FoodDaoImpl() {
		foods = new ArrayList();
		foods.add(new Food(0, "Shopska salata", 3.5));
		foods.add(new Food(1, "Burger", 4.2));
		foods.add(new Food(2, "Duner", 2.2));
	}

	@Override
	public List<Food> listFoods() {
		return foods;
	}

	@Override
	public Food getById(int id) {
		for (Food food : foods) {
			if (food.id == id) {
				return food;
			}
		}
		return null;
	}
}
