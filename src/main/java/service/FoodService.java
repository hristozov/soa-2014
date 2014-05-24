package service;

import com.google.inject.Inject;
import dao.FoodDao;
import model.Food;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("food")
@WebService
public class FoodService {
	@Inject
	public FoodDao foodDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public List<Food> list() {
		return foodDao.listFoods();
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public Food getById(@PathParam("id") @WebParam(name = "id") int id) {
		return foodDao.getById(id);
	}
}
