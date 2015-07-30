package selcpkg;

public class Group {
	private static int recbookNum = 0;
	private String groupNum;
	Student[] students = new Student[10];

	public Group(String groupNum) {
		super();
		this.groupNum = groupNum;
	}

	public String getGroupNum() {
		return this.groupNum;
	}

	public void addStudent(Student st) throws GroupIsFullException,
			DuplicationException {
		boolean vacant = false;
		if (st.getRecbookNum() == 0) {
			for (int i = 0; i < this.students.length; i++) {
				if (this.students[i] == null) {
					this.students[i] = st;
					st.setGroup(this);
					st.setRecbookNum(++Group.recbookNum);
					vacant = true;
					break;
				}
			}
			if (!vacant) {
				throw new GroupIsFullException();
			}
		} else {
			throw new DuplicationException();
		}
	}

	public void findStudent(String sirname) {
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i] != null
					&& this.students[i].getSirname() == sirname) {
				System.out.println(this.students[i].toString());
			}
		}
	}

	public void excludeStudent(int rbNum) {
		boolean stFound = false;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i] != null
					&& this.students[i].getRecbookNum() == rbNum) {
				this.students[i].setRecbookNum(0);
				System.out.println("Student " + this.students[i].getSirname()
						+ " excluded");
				this.students[i] = null;
				stFound = true;
				break;
			}
		}
		if (!stFound) {
			System.out.println("No such student!");
		}
	}

	public void listStudents() {
		int index = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i] != null) {
				System.out
						.println(++index + ") " + this.students[i].toString());
			}
		}
		System.out.println();
	}
}
