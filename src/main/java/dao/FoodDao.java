package dao;

import com.google.inject.ImplementedBy;
import model.Food;

import java.util.List;

@ImplementedBy(FoodDaoImpl.class)
public interface FoodDao {
	List<Food> listFoods();
	Food getById(int id);
}
