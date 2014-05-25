package service;

import com.google.inject.Inject;
import dao.UserDao;
import model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@WebService
@Path("user")
public class UserService {
	@Inject
	public UserDao userDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public List<User> list() {
		return userDao.list();
	}
}
