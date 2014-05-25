package dao;

import com.google.inject.ImplementedBy;
import model.User;

import java.util.List;

@ImplementedBy(UserDaoImpl.class)
public interface UserDao {
	User getById(int id);
	List<User> list();
}
