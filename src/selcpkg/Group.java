package selcpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.TreeSet;

public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int recbookNum = 0;
	private static int groupMaxSize = 10;
	private String grName;
	private TreeSet<Student> students = new TreeSet<Student>();

	public Group(String grName) {
		super();
		this.grName = grName;
	}

	public static int getGroupMaxSize() {
		return groupMaxSize;
	}

	public int getGroupSize() {
		return students.size();
	}

	public String getGroupName() {
		return grName;
	}

	public Student getStudent(int index) {
		Student result = null;
		int i = 0;
		for (Student st : students) {
			if (i == index) {
				result = st;
			}
			i++;
		}
		return result;
	}

	public void resetGroup() {
		for (Student st : students) {
			st.setRecbookNum(0);
			st.setGroup(null);
		}
		students.clear();
	}

	public void saveToFile() {
		String filename = this.grName + ".txt";
		try (PrintWriter fw = new PrintWriter(filename)) {
			fw.printf("%-30s\t%-30s\t%s\t%s\t%s\n", "Sirname", "Name", "Age", "Sex", "Recbook");
			for (Student st : students)
				fw.print(st.returnFormatedInfo());
		} catch (IOException e) {
			System.out.println("Error creating " + filename);
		} catch (NullPointerException e) {
		}
		System.out.println("File successfully created!");
	}

	public void addStudent(Student st) throws GroupIsFullException, DuplicationException {
		if (st.getRecbookNum() != 0 || this.existsInGroup(st)) {
			throw new DuplicationException();
		} else if (students.size() < groupMaxSize) {
			students.add(st);
			st.setGroup(this);
			st.setRecbookNum(++Group.recbookNum);
		} else {
			throw new GroupIsFullException();
		}
	}

	public void findStudent(String sirname) {
		StringBuffer results = new StringBuffer();
		for (Student st : students) {
			if (st.getSirname().matches("^" + sirname + ".*")) {
				results.append(st.toString() + "\n");
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

		for (Student st : students) {
			if (st.getRecbookNum() == rbNum) {
				students.remove(st);
				st.setRecbookNum(0);
				st.setGroup(null);
				System.out.println("Student " + st.getSirname() + " " + st.getName() + " excluded");
				stFound = true;
				break;
			}
		}
		if (!stFound) {
			System.out.println("No such student!");
		}
	}

	public void listStudents() {
		if (students.size() == 0) {
			System.out.println("The group is empty!");
		} else {
			int index = 0;
			for (Student st : students) {
				System.out.println(++index + ") " + st);
			}
		}
		System.out.println();
	}

	public void interactiveAdd() {

		System.out.println("Adding new student to group " + this.getGroupName());
		String name = Interactive.askUser("Input student sirname", "[А-ЯA-Z][а-яa-z]+");
		String sirname = Interactive.askUser("Input student name", "[А-ЯA-Z][а-яa-z]+");
		int age = Integer.parseInt(Interactive.askUser("Input student age", "[1-7][0-9]"));
		char gender = Interactive.askUser("Input student gender 'm' or 'f'", "[mf]").charAt(0);
		try {
			this.addStudent(new Student(name, sirname, age, gender));
		} catch (GroupIsFullException | DuplicationException e) {
			System.out.println("Error: " + e);
		}
	}

	private boolean existsInGroup(Student st) {
		return students.contains(st);
	}
}
