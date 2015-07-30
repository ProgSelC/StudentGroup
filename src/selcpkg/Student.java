package selcpkg;

public class Student extends Human {
	private Group group;
	private int recbookNum;
	
	public Student(String name, String sirname, int age, char gender) {
		super(name, sirname, age, gender);
		recbookNum = 0;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroup() {
		return group.getGroupNum();
	}
	
	public void setRecbookNum(int rbNum){
		this.recbookNum = rbNum;
	}
	
	public int getRecbookNum(){
		return this.recbookNum;
	}

	@Override
	public String toString() {
		String grNum = (group == null) ? "No group" : group.getGroupNum();
		return super.toString() + " group:" + grNum + " recbook:" + recbookNum;
	}

}
