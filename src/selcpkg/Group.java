package selcpkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Group {
	private static int recbookNum = 0;
	private String groupNum;
	private Student[] students = new Student[10];

	public Group(String groupNum) {
		super();
		this.groupNum = groupNum;
		File fl = new File(groupNum + ".txt");
		if (fl.exists()) {
			this.initFromFile(groupNum + ".txt");
		}
	}

	public String getGroupNum() {
		return this.groupNum;
	}

	public Student getStudent(int index) {
		return this.students[index];
	}

	public void resetGroup() {
		for (int i = 0; i < students.length; i++) {
			if (this.students[i] != null) {
				this.students[i].setRecbookNum(0);
				this.students[i].setGroup(null);
				this.students[i] = null;
			}
		}
	}

	public void initFromFile(String filename) {
		String text = "";
		int maxRbNum = Group.recbookNum;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (int i = -1; i < 10 && (text = br.readLine()) != null; i++) {
				if (i >= 0) {
					String[] a = text.split("\\s+");
					this.students[i] = new Student(a[0], a[1],
							Integer.parseInt(a[2]), a[3].charAt(0), this,
							Integer.parseInt(a[4]));
					maxRbNum = (maxRbNum > Integer.parseInt(a[4])) ? maxRbNum
							: Integer.parseInt(a[4]);
				}
			}
			Group.recbookNum = maxRbNum;
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

	public void saveToFile() {
		String filename = this.groupNum + ".txt";
		try (PrintWriter fw = new PrintWriter(filename)) {
			fw.printf("%-30s\t%-30s\t%s\t%s\t%s\n", "Sirname", "Name", "Age",
					"Sex", "Recbook");
			for (Student st : students)
				fw.print(st.returnFormatedInfo());
		} catch (IOException e) {
			System.out.println("Error creating " + filename);
		} catch (NullPointerException e) {
		}
	}

	public void addStudent(Student st) throws GroupIsFullException,
			DuplicationException {
		boolean vacant = false;
		if (st.getRecbookNum() != 0 || this.existsInGroup(st)) {
			throw new DuplicationException();
		} else {
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
		}
	}

	public void findStudent(String sirname) {
		StringBuffer results = new StringBuffer();
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i] != null
					&& this.students[i].getSirname().matches(
							"^" + sirname + ".*")) {
				results.append(this.students[i].toString() + "\n");
			}
		}
		if (results.length() > 0) {
			System.out.println("Search results:");
			System.out.println(results);
		} else {
			System.out.println("Nothing found\n");
		}
	}

	public void excludeStudent(int rbNum) {
		boolean stFound = false;
		int stNumber = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i] != null
					&& this.students[i].getRecbookNum() == rbNum) {
				this.students[i].setRecbookNum(0);
				System.out.println("Student " + this.students[i].getSirname()
						+ " " + this.students[i].getName() + " excluded");
				this.students[i].setGroup(null);
				this.students[i] = null;
				stFound = true;
				stNumber = i;
				break;
			}
		}
		if (!stFound) {
			System.out.println("No such student!");
		} else {
			for (int i = stNumber; i < this.students.length - 1; i++) {
				this.students[i] = this.students[i + 1];
				this.students[i + 1] = null;
			}
		}
	}

	public void listStudents() {
		int index = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i] != null) {
				System.out
						.printf("%2d) %s\n",++index,this.students[i].toString());
			}
		}
		System.out.println();
	}

	public void sortStudents() {
		try {
			Arrays.sort(students);
		} catch (NullPointerException e) {

		}
	}

	public void interactiveAdd() {

		System.out.println("Adding new student to group " + this.getGroupNum());
		String name = Interactive.askUser("Input student sirname",
				"[А-ЯA-Z][а-яa-z]+");
		String sirname = Interactive.askUser("Input student name",
				"[А-ЯA-Z][а-яa-z]+");
		int age = Integer.parseInt(Interactive.askUser("Input student age",
				"[1-7][0-9]"));
		char gender = Interactive.askUser("Input student gender 'm' or 'f'",
				"[mf]").charAt(0);
		try {
			this.addStudent(new Student(name, sirname, age, gender));
		} catch (GroupIsFullException | DuplicationException e) {
			System.out.println("Error: " + e);
		}
	}

	private boolean existsInGroup(Student st) {
		boolean result = false;
		for (Student grStudent : this.students) {
			if (grStudent != null) {
				if (grStudent.getSirname().equals(st.getSirname())
						&& grStudent.getName().equals(st.getName())
						&& grStudent.getGender() == st.getGender()
						&& grStudent.getAge() == st.getAge()) {
					result = true;
				}
			}
		}
		return result;
	}
}
