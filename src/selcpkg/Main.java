package selcpkg;

public class Main {

	public static void main(String[] args) {
		Group gr = new Group("JavaOOP-1");
		Student st[] = { new Student("Пупкин", "Владимир", 21, 'm'), new Student("Алексеева", "Инна", 18, 'f'),
				new Student("Федоров", "Семен", 18, 'm'), new Student("Пупкин", "Василий", 17, 'm'),
				new Student("Алексеева", "Ирина", 22, 'f'), new Student("Лукова", "Алевтина", 19, 'f'),
				new Student("Зуб", "Федор", 21, 'm'), new Student("Апрельская", "Маргарита", 24, 'f'),
				new Student("Федоров", "Александр", 18, 'm'), new Student("Задорнов", "Василий", 19, 'm'),
				new Student("Лишняя", "Запись", 20, 'f') };

		if (Interactive.askUser("Do you want to init group with sample data y/n", "[yn]").charAt(0) == 'y') {
			gr.resetGroup();
			gr.listStudents();
			for (Student stud : st) {
				addStud(gr, stud);
			}
			gr.listStudents();
		}
		boolean exit = false;
		while (!exit) {
			switch (Interactive.askUser("Choose an action [help|show|find|sort|add|del|reset|exit]",
					"help|show|find|sort|add|del|reset|exit")) {
			case "help": 
				System.out.println("\thelp - shows this help-message");
				System.out.println("\tshow - show list of students in group");
				System.out.println("\tfind - find students by firs letter of sirname");
				System.out.println("\tsort - sort students by sirname-name key");
				System.out.println("\tadd - interactively add a new student");
				System.out.println("\tdel - delete a student from group by id(record book number)");
				System.out.println("\treset - exclude all students");
				System.out.println("\texit - on exit the group-list is sorted and saved to file");
				break;
			case "show":
				gr.listStudents();
				break;
			case "find":
				gr.findStudent(
						Interactive.askUser("Input student sirname (or some first letters)", "[А-ЯA-Z][а-яa-z]+"));
				break;
			case "sort":
				gr.sortStudents();
				break;
			case "add":
				gr.interactiveAdd();
				break;
			case "del":
				gr.excludeStudent(Integer
						.parseInt(Interactive.askUser("Please, input students recbook number", "[0-9]+")));
				break;
			case "reset":
				gr.resetGroup();
				break;
			case "exit": 
				gr.sortStudents();
				gr.saveToFile();
				exit = true;
				break;
			}
		}
	}

	static void addStud(Group grp, Student stud) {
		try {
			grp.addStudent(stud);
		} catch (GroupIsFullException | DuplicationException e) {
			System.out.println("Error: " + e);
		}
	}
}
