package service;

import com.google.inject.Inject;
import dao.FooDao;
import model.Foo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("foo")
public class FooService {
	@Inject
	public FooDao fooDao;

	@Path("bar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Foo getBar() {
		return fooDao.createFoo();
	}
}
