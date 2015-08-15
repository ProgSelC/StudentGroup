package selcpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		GroupArray groups = new GroupArray();
		Group gr = null;
		String index = null;
		String grName = null;
		Student st[] = { new Student("Пупкин", "Владимир", 21, 'm'), new Student("Алексеева", "Инна", 18, 'f'),
				new Student("Федоров", "Семен", 18, 'm'), new Student("Пупкин", "Василий", 17, 'm'),
				new Student("Алексеева", "Ирина", 22, 'f'), new Student("Лукова", "Алевтина", 19, 'f'),
				new Student("Зуб", "Федор", 21, 'm'), new Student("Апрельская", "Маргарита", 24, 'f'),
				new Student("Федоров", "Александр", 18, 'm'), new Student("Задорнов", "Василий", 19, 'm') };
		boolean sampleUsed = false;

		boolean exit = false;
		while (!exit) {
			while (gr == null && !exit) {
				switch (Interactive.askUser("Choose an action [type help for more info] ",
						"help|newgr|restoregr|selectgr|listgr|delgr|exit")) {
				case "help":
					System.out.println("\thelp - show this help-message");
					System.out.println("\tnewgr - create new group");
					System.out.println("\trestoregr - restore a group from binary(if exists)");
					System.out.println("\tselectgr - select group by index from list");
					System.out.println("\tlistgr - show list of existing groups");
					System.out.println("\tdelgr - delete a group by index");
					System.out.println("\texit - exit");
					break;
				case "newgr":
					grName = Interactive.askUser("Please input group name", ".*");
					gr = groups.addGroup(grName);
					if (!sampleUsed) {
						if (Interactive.askUser("Do you want to init group with sample data y/n", "[yn]")
								.charAt(0) == 'y') {
							gr.resetGroup();
							for (Student stud : st) {
								addStud(gr, stud);
							}
							System.out.println("Group %s successfully created!\n" + gr.getGroupName());
							sampleUsed = true;
						}
					}
					break;
				case "restoregr":
					grName = Interactive.askUser("Please input group name", ".*");
					gr = restoreGroup(grName);
					if (!(gr == null)) {
						gr = groups.addGroup(restoreGroup(grName));
					} else {
						System.out.println("Error adding group: restore method returned null");
					}
					break;
				case "selectgr":
					groups.listGroups();
					index = Interactive.askUser("Please enter a group Index", "[0-9]+");
					gr = groups.getGroup(Integer.parseInt(index));
					break;
				case "listgr":
					groups.listGroups();
					break;
				case "delgr":
					groups.listGroups();
					index = Interactive.askUser("Please enter a group Index", "[0-9]+");
					groups.delGroup(Integer.parseInt(index));
					break;
				case "exit":
					exit = true;
					System.out.println("Bye-bye!");
					break;
				}
			}
			while (!(gr == null)) {
				switch (Interactive.askUser(
						"Choose an action [type help for more info] " + gr.getGroupName() + " selected",
						"help|show|find|sort|add|del|reset|save|serialize|chgr|exit")) {
				case "help":
					System.out.println("\thelp - shows this help-message");
					System.out.println("\tshow - show list of students in group");
					System.out.println("\tfind - find students by firs letter of sirname");
					System.out.println("\tsort - sort students by sirname-name key");
					System.out.println("\tadd - interactively add a new student");
					System.out.println("\tdel - delete a student from group by id(record book number)");
					System.out.println("\treset - exclude all students");
					System.out.println("\tsave - save to txt file");
					System.out.println("\tserialize - save to binary file");
					System.out.println("\tchgr - select/create other group");
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
					gr.excludeStudent(
							Integer.parseInt(Interactive.askUser("Please, input students recbook number", "[0-9]+")));
					break;
				case "reset":
					gr.resetGroup();
					break;
				case "save":
					gr.sortStudents();
					gr.saveToFile();
					break;
				case "serialize":
					serializeGroup(gr);
					break;
				case "chgr":
					gr = null;
					break;
				case "exit":
					gr = null;
					exit = true;
					System.out.println("Bye-bye!");
					break;
				}
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

	static void serializeGroup(Group gr) {
		try (ObjectOutputStream objStream = new ObjectOutputStream(new FileOutputStream(gr.getGroupName() + ".obj"))) {
			objStream.writeObject(gr);
			System.out.printf("Group %s serialized to file %s\n", gr.getGroupName(), gr.getGroupName() + ".obj");
		} catch (IOException e) {
			System.out.println("ERROR saving group !!!");
		}
	}

	static Group restoreGroup(String grName) {
		Group gr = null;
		File fl = new File(grName + ".obj");
		if (fl.exists() && fl.isFile()) {
			try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(grName + ".obj"))) {
				gr = (Group) OIS.readObject();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("ERROR loading group !!!");
			}
		} else {
			System.out.println("File conaining group data doesn't exist!");
		}
		return gr;
	}
}