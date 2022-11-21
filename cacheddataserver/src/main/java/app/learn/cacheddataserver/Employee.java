package app.learn.cacheddataserver;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = -6032930432768273488L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String email;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + "]\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
