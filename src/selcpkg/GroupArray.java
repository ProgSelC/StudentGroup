package selcpkg;

public class GroupArray {
	private Group[] grArr;

	public GroupArray() {
		super();
	}

	public Group getGroup(int i) {
		if (grArr == null || i > grArr.length) {
			System.out.println("No groups in list!");
			return null;
		} else {
			return grArr[i - 1];
		}
	}

	public boolean groupExists(String grName) {
		boolean result = false;
		if (grArr != null) {
			for (Group gr : grArr) {
				if (gr.getGroupName().equals(grName)) {
					result = true;
				}
			}
		}
		return result;
	}

	public int getGroupsQuan() {
		if (grArr != null) {
			return grArr.length;
		} else {
			return 0;
		}
	}

	public Group addGroup(String grName) {
		Group result = null;
		if (grArr == null) {
			grArr = new Group[1];
			grArr[0] = new Group(grName);
			result = grArr[0];
		} else if (!this.groupExists(grName)) {
			Group[] newGr = new Group[grArr.length + 1];
			System.arraycopy(grArr, 0, newGr, 0, grArr.length);

			grArr = newGr;
			result = new Group(grName);
			grArr[grArr.length - 1] = result;
		} else {
			System.out.printf("Error adding new group: %s already exists!\n", grName);
		}
		return result;
	}

	public Group addGroup(Group gr) {
		Group result = null;
		if (grArr == null) {
			grArr = new Group[1];
			grArr[0] = gr;
			result = grArr[0];
		} else if (!this.groupExists(gr.getGroupName())) {
			Group[] newGr = new Group[grArr.length + 1];
			System.arraycopy(grArr, 0, newGr, 0, grArr.length);

			grArr = newGr;
			result = gr;
			grArr[grArr.length - 1] = result;
		} else {
			System.out.printf("Error adding new group: %s already exists!\n", gr.getGroupName());
		}
		return result;
	}

	public void delGroup(int index) {
		if (grArr == null) {
			System.out.println("Group list is empty!");
		} else if (index > grArr.length || index < 1) {
			System.out.println("Wrong index!");
		} else {
			grArr[index - 1].resetGroup();
			if (grArr.length == 1) {
				grArr = null;
			} else {
				for (int i = (index - 1); i < grArr.length - 1; i++) {
					grArr[i] = grArr[i + 1];
				}
				Group[] newGr = new Group[grArr.length - 1];
				System.arraycopy(grArr, 0, newGr, 0, newGr.length);
				grArr = newGr;
			}
			System.out.println("Group successfully deleted!");
		}

	}

	public void listGroups() {
		int i = 0;
		if (grArr == null) {
			System.out.println("No groups in list!");
		} else {
			for (Group gr : grArr) {
				System.out.printf("%2d) %s\n", i + 1, gr.getGroupName());
				i++;
			}
		}
	}
}
