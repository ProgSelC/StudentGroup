package selcpkg;

public class Student extends Human implements Comparable<Student> {
	
	private static final long serialVersionUID = 1L;
	
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

	public String returnFormatedInfo() {
		return String.format("%-30s\t%-30s\t%d\t%s\t%d\n", this.getSirname(),
				this.getName(), this.getAge(), this.getGender(),
				this.getRecbookNum());
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroup() {
		return group.getGroupName();
	}

	public void setRecbookNum(int rbNum) {
		this.recbookNum = rbNum;
	}

	public int getRecbookNum() {
		return this.recbookNum;
	}

	@Override
	public String toString() {
		String grNum = (group == null) ? "No group" : group.getGroupName();
		return String.format("%s\tgroup: %s\trecbook: %d", super.toString(),
				grNum, recbookNum);
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
