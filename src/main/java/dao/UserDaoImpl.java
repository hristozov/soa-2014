package dao;

import com.google.inject.Singleton;
import model.User;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserDaoImpl implements UserDao {
	private final List<User> users;

	public UserDaoImpl() {
		users = new ArrayList<>();
		users.add(new User(0, "Ivan Petrov"));
		users.add(new User(1, "Stefan Georgiev"));
		users.add(new User(2, "Petar Pavlov"));
	}

	@Override
	public User getById(int id) {
		for (User user : users) {
			if (id == user.id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> list() {
		return users;
	}
}
