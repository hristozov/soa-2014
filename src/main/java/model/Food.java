package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "food")
public class Food {
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 5)
	public int id;

	@Id
	@Column(name = "name", unique = false, nullable = false, length = 20)
	public String name;

	@Id
	@Column(name = "price", unique = false, nullable = false, precision = 5)
	public Double price;

	public Food() {
	}

	public Food(int id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}
