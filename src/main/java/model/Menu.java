package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Menu {
	public int id;
	public String name;
	public String description;
	public List<Food> contents;

	public Menu() {
	}

	public Menu(int id, String name, String description, List<Food> contents) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.contents = contents;
	}

	public Double getPrice() {
		double result = 0;
		for (Food food : contents) {
			result += food.price;
		}
		return result;
	}
}
