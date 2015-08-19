package selcpkg;

import java.io.Serializable;

public class Human implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String sirname;
	private String name;
	private int age;
	private char gender;

	public Human(String sirname, String name, int age, char gender) {
		super();
		this.sirname = sirname;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public void setSirname(String sirname) {
		this.sirname = sirname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSirname() {
		return sirname;
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}

	public char getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return String.format("%-30s\t%-30s\tage: %d\tgender: %s", this.sirname,
				this.name, this.age, this.gender);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + gender;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sirname == null) ? 0 : sirname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (age != other.age)
			return false;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sirname == null) {
			if (other.sirname != null)
				return false;
		} else if (!sirname.equals(other.sirname))
			return false;
		return true;
	}
}
