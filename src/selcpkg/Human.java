package selcpkg;

public class Human {
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
		return this.sirname + " " + this.name + "\tage:" + this.age + " gender:"
				+ this.gender;
	}
}
