package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Order {
	public int id;
	public List<Food> contents;

	public Order(int id, List<Food> contents) {
		this.id = id;
		this.contents = contents;
	}

	public Order() {
	}
}
