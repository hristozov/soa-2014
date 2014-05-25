package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement
public class Order {
	public int id;
	public List<Food> contents;
	public boolean confirmed = false;
	public User user;

	public Order(int id, List<Food> contents) {
		this.id = id;
		this.contents = contents;
	}

	public Order() {
	}

	@XmlTransient
	public Double getPrice() {
		double result = 0;
		for (Food food : contents) {
			result += food.price;
		}
		return result;
	}
}
