package dao;

import com.google.inject.ImplementedBy;
import model.Menu;

import java.util.List;

@ImplementedBy(MenuDaoImpl.class)
public interface MenuDao {
	public List<Menu> listMenus();
	public Menu getById(int id);
}
