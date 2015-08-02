package selcpkg;

public class Student extends Human implements Comparable<Student> {
	private Group group;
	private int recbookNum;

	public Student(String name, String sirname, int age, char gender) {
		super(name, sirname, age, gender);
		recbookNum = 0;
	}

	public Student(String name, String sirname, int age, char gender, Group gr, int rb) {
		super(name, sirname, age, gender);
		group = gr;
		recbookNum = rb;
	}

	public String returnTabSepInfo() {
		return this.getSirname() + "\t" + this.getName() + "\t" + this.getAge() + "\t" + this.getGender() + "\t"
				+ this.getRecbookNum() + "\n";
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroup() {
		return group.getGroupNum();
	}

	public void setRecbookNum(int rbNum) {
		this.recbookNum = rbNum;
	}

	public int getRecbookNum() {
		return this.recbookNum;
	}

	@Override
	public String toString() {
		String grNum = (group == null) ? "No group" : group.getGroupNum();
		return super.toString() + " group:" + grNum + " recbook:" + recbookNum;
	}

	@Override
	public int compareTo(Student anotherStudent) {
		int result = this.getSirname().compareTo(anotherStudent.getSirname());
		if (result == 0) {
			result = this.getName().compareTo(anotherStudent.getName());
		}
		return result;
	}

}
