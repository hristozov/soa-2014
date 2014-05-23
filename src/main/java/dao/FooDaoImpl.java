package dao;

import model.Foo;

public class FooDaoImpl implements FooDao {
	@Override
	public Foo createFoo() {
		return new Foo();
	}
}
