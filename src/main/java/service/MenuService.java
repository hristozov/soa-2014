package service;

import com.google.inject.Inject;
import dao.MenuDao;
import model.Food;
import model.Menu;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("menu")
@WebService
public class MenuService {
	@Inject
	public MenuDao menuDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public List<Menu> list() {
		return menuDao.listMenus();
	}
}
