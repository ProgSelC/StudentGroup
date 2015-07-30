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

	public String getName() {
		return name;
	}

	public String getSirname() {
		return sirname;
	}

	public int getAge() {
		return age;
	}

	public char getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return this.sirname + " " + this.name + " age:" + this.age + " gender:"
				+ this.gender;
	}
}
