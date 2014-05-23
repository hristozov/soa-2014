package dao;

import com.google.inject.ImplementedBy;
import model.Foo;

@ImplementedBy(FooDaoImpl.class)
public interface FooDao {
	Foo createFoo();
}
