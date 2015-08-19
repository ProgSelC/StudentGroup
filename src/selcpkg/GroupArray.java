package selcpkg;

import java.util.ArrayList;

public class GroupArray {
	private ArrayList<Group> grArr = new ArrayList<Group>();

	public GroupArray() {
		super();
	}

	public Group getGroup(int index) {
		Group result = null;
		if (index > grArr.size()) {
			System.out.println("No groups in list!");
		} else {
			result = grArr.get(index - 1);
		}
		return result;
	}

	public boolean groupExists(String grName) {
		boolean result = false;
			for (Group gr : grArr) {
				if (gr.getGroupName().equals(grName)) {
					result = true;
					break;
				}
		}
		return result;
	}

	public int getGroupsQuan() {
		return grArr.size();
	}

	public Group addGroup(String grName) {
		Group result = null;
		if (!this.groupExists(grName)) {
			result = new Group(grName);
			grArr.add(result);
		} else {
			System.out.printf("Error adding new group: %s already exists!\n", grName);
		}
		return result;
	}

	public Group addGroup(Group gr) {
		Group result = null;
		if (!this.groupExists(gr.getGroupName())) {
			result = gr;
			grArr.add(gr);
		} else {
			System.out.printf("Error adding new group: %s already exists!\n", gr.getGroupName());
		}
		return result;
	}

	public void delGroup(int index) {
		if (grArr.size() == 0) {
			System.out.println("Group list is empty!");
		} else if (index > grArr.size() || index < 1) {
			System.out.println("Wrong index!");
		} else {
			grArr.get(index - 1).resetGroup();
			grArr.remove(index - 1);
			System.out.println("Group successfully deleted!");
		}
	}

	public void listGroups() {
		if (grArr.size() == 0) {
			System.out.println("No groups in list!");
		} else {
			int i = 0;
			for (Group gr : grArr) {
				System.out.printf("%2d) %s\n", i + 1, gr.getGroupName());
				i++;
			}
		}
	}
}
