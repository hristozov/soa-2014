package service;

import com.google.inject.Inject;
import dao.FoodDao;
import model.Food;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("food")
public class FoodService {
	@Inject
	public FoodDao foodDao;

	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Food> list() {
		return foodDao.listFoods();
	}

	@Path("get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Food getById(@PathParam("id") int id) {
		return foodDao.getById(id);
	}
}
