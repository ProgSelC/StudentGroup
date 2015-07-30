package selcpkg;

public class Main {

	public static void main(String[] args) {
		Group gr = new Group("JavaOOP");
		Student st[] = { new Student("Pupkin0", "V", 21, 'm'),
				new Student("Pupkin1", "V", 21, 'f'),
				new Student("Pupkin2", "V", 21, 'm'),
				new Student("Pupkin3", "V", 21, 'm'),
				new Student("Pupkin4", "V", 21, 'f'),
				new Student("Pupkin5", "V", 21, 'f'),
				new Student("Pupkin6", "V", 21, 'm'),
				new Student("Pupkin7", "V", 21, 'f'),
				new Student("Pupkin8", "V", 21, 'm'),
				new Student("Pupkin9", "V", 21, 'm'),
				new Student("Pupkin-", "V", 21, 'f') };

		// В цикле добавляем студентов в группу, последний - лишний

		for (Student stud : st) {
			addStud(gr, stud);
		}

		gr.listStudents();
		
		gr.findStudent("Pupkin1");

		// 5-го студента исключаем
		gr.excludeStudent(st[5].getRecbookNum());
		gr.listStudents();

		// Пытаемся дабавить студента, который уже есть в группе
		addStud(gr, st[1]);

		// Появилось вакантное место, можем принять студента
		addStud(gr, st[10]);
		gr.listStudents();
	}

	static void addStud(Group grp, Student stud) {
		try {
			grp.addStudent(stud);
		} catch (GroupIsFullException e) {
			System.out.println("Error: " + e);
		} catch (DuplicationException e) {
			System.out.println("Error: " + e);
		}
	}
}
